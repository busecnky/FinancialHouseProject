package com.financialhouseproject.service;

import com.financialhouseproject.dto.request.AuthRequestDto;
import com.financialhouseproject.dto.response.AuthResponseDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private AuthService authService;

    @Value("${baseUrl}/merchant/user/login")
    private String loginURL;

    @Test
    public void testAuthenticate_Success() {
        AuthRequestDto authRequest = new AuthRequestDto("username", "password");
        AuthResponseDto expectedResponse = new AuthResponseDto("token", "status");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<AuthRequestDto> requestEntity = new HttpEntity<>(authRequest, headers);

        Mockito.when(restTemplate.postForEntity(
                        loginURL,
                        requestEntity,
                        AuthResponseDto.class))
                .thenReturn(new ResponseEntity<>(expectedResponse, HttpStatus.OK));

        AuthResponseDto actualResponse = authService.authenticate(authRequest);

        Assertions.assertNotNull(actualResponse);
        Assertions.assertEquals("token", actualResponse.token());

        Mockito.verify(restTemplate, Mockito.times(1))
                .postForEntity(Mockito.eq(loginURL), Mockito.eq(requestEntity), Mockito.eq(AuthResponseDto.class));
    }

    @Test
    public void testAuthenticate_HttpClientErrorException_Simplified() {
        AuthRequestDto authRequest = new AuthRequestDto("username", "password");

        HttpClientErrorException exception = Mockito.mock(HttpClientErrorException.class);
        Mockito.when(exception.getResponseBodyAs(AuthResponseDto.class))
                .thenReturn(new AuthResponseDto(null, "Invalid credentials"));

        Mockito.when(restTemplate.postForEntity(
                        Mockito.eq(loginURL),
                        Mockito.any(HttpEntity.class),
                        Mockito.eq(AuthResponseDto.class)))
                .thenThrow(exception);

        AuthResponseDto actualResponse = authService.authenticate(authRequest);

        Assertions.assertNotNull(actualResponse);
        Assertions.assertNull(actualResponse.token());
        Assertions.assertEquals("Invalid credentials", actualResponse.status());

        Mockito.verify(restTemplate, Mockito.times(1))
                .postForEntity(Mockito.eq(loginURL), Mockito.any(HttpEntity.class), Mockito.eq(AuthResponseDto.class));

    }

    @Test
    public void testAuthenticate_UnexpectedError_ThrowsIllegalStateException() {
        AuthRequestDto authRequest = new AuthRequestDto("username", "password");

        Mockito.when(restTemplate.postForEntity(
                        Mockito.eq(loginURL),
                        Mockito.any(HttpEntity.class),
                        Mockito.eq(AuthResponseDto.class)))
                .thenThrow(new NullPointerException("Unexpected exception"));

        IllegalStateException thrown = Assertions.assertThrows(IllegalStateException.class,
                () -> authService.authenticate(authRequest));

        Assertions.assertEquals("Unexpected error during authentication", thrown.getMessage());
        Assertions.assertInstanceOf(NullPointerException.class, thrown.getCause());
    }


}
