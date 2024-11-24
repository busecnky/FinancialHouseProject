package com.financialhouseproject.helpers;

import com.financialhouseproject.dto.request.TestRequestDto;
import com.financialhouseproject.dto.response.TestResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HttpRequestHandlerTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private HttpRequestHandler httpRequestHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void sendPostRequest_shouldReturnResponseBody_whenRequestIsSuccessful() {
        String url = "https://api.example.com/resource";
        String authToken = "token";
        TestRequestDto requestDto = new TestRequestDto("test-data");
        TestResponseDto expectedResponse = new TestResponseDto("response-data");

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authToken);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<TestRequestDto> requestEntity = new HttpEntity<>(requestDto, headers);
        ResponseEntity<TestResponseDto> responseEntity = new ResponseEntity<>(expectedResponse, HttpStatus.OK);

        when(restTemplate.postForEntity(url, requestEntity, TestResponseDto.class))
                .thenReturn(responseEntity);

        TestResponseDto actualResponse = httpRequestHandler.sendPostRequest(
                url, authToken, requestDto, TestResponseDto.class);

        assertEquals(expectedResponse, actualResponse);
        verify(restTemplate, times(1)).postForEntity(url, requestEntity, TestResponseDto.class);
    }

    @Test
    void sendPostRequest_shouldThrowException_whenResponseBodyIsEmpty() {
        String url = "https://api.example.com/resource";
        String authToken = "token";
        TestRequestDto requestDto = new TestRequestDto("test-data");

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authToken);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<TestRequestDto> requestEntity = new HttpEntity<>(requestDto, headers);
        ResponseEntity<TestResponseDto> responseEntity = new ResponseEntity<>(null, HttpStatus.OK);

        when(restTemplate.postForEntity(url, requestEntity, TestResponseDto.class))
                .thenReturn(responseEntity);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () ->
                httpRequestHandler.sendPostRequest(url, authToken, requestDto, TestResponseDto.class));
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, exception.getStatusCode());
        assertTrue(Objects.requireNonNull(exception.getReason()).contains("Empty response body"));
    }

    @Test
    void validateToken_shouldThrowUnauthorizedException_whenTokenIsInvalid() {
        String url = "https://api.example.com/resource";
        String authToken = "Bearer invalid-token";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authToken);
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        when(restTemplate.exchange(url, HttpMethod.HEAD, requestEntity, Void.class))
                .thenThrow(new HttpClientErrorException(HttpStatus.UNAUTHORIZED));

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () ->
                httpRequestHandler.sendPostRequest(url, authToken, null, Void.class));
        assertEquals(HttpStatus.UNAUTHORIZED, exception.getStatusCode());
        assertTrue(Objects.requireNonNull(exception.getReason()).contains("Invalid or expired token"));
    }
    @Test
    void sendPostRequest_shouldThrowResponseStatusException_whenHttpClientErrorExceptionOccurs() {
        String url = "https://api.example.com/resource";
        String authToken = "token";
        TestRequestDto requestDto = new TestRequestDto("test-data");

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authToken);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<TestRequestDto> requestEntity = new HttpEntity<>(requestDto, headers);

        when(restTemplate.postForEntity(eq(url), eq(requestEntity), eq(TestResponseDto.class)))
                .thenThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND, "Resource not found"));

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () ->
                httpRequestHandler.sendPostRequest(url, authToken, requestDto, TestResponseDto.class));

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, exception.getStatusCode());
        assertTrue(Objects.requireNonNull(exception.getReason()).contains("An error occurred during the request"));
    }

}
