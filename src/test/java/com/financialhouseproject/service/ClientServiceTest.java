package com.financialhouseproject.service;

import com.financialhouseproject.dto.request.ClientInfoRequestDto;
import com.financialhouseproject.dto.response.ClientInfoResponseDto;
import com.financialhouseproject.dto.response.models.CustomerInfo;
import com.financialhouseproject.helpers.HttpRequestHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {
    @Mock
    private HttpRequestHandler httpRequestHandler;

     @Mock
     private CustomerInfo customerInfo;

    @InjectMocks
    private ClientService clientService;

    @Value("${baseUrl}/client")
    private String clientUrl;

    @Test
    public void testFetchClient_Success(){
        String authToken = "token";
        ClientInfoRequestDto requestDto = new ClientInfoRequestDto("transactionId");
        ClientInfoResponseDto responseDto = new ClientInfoResponseDto(customerInfo);

        Mockito.when(httpRequestHandler.sendPostRequest(
                clientUrl,
                authToken,
                requestDto,
                ClientInfoResponseDto.class))
                .thenReturn(responseDto);

        ClientInfoResponseDto actualResponse = clientService.fetchClient(authToken, requestDto);

        Assertions.assertNotNull(actualResponse);
        Assertions.assertEquals(responseDto, actualResponse);

        Mockito.verify(httpRequestHandler, Mockito.times(1))
                .sendPostRequest(clientUrl, authToken, requestDto, ClientInfoResponseDto.class);

    }
}
