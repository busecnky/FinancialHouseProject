package com.financialhouseproject.service;

import com.financialhouseproject.dto.request.ClientInfoRequestDto;
import com.financialhouseproject.dto.response.ClientInfoResponseDto;
import com.financialhouseproject.helpers.HttpRequestHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    private final HttpRequestHandler httpRequestHandler;
    @Value("${baseUrl}/client")
    private String clientUrl;


    public ClientService(HttpRequestHandler httpRequestHandler) {
        this.httpRequestHandler = httpRequestHandler;
    }

    public ClientInfoResponseDto fetchClient(
            String authToken,
            ClientInfoRequestDto clientInfoRequestDto) {
        return httpRequestHandler.sendPostRequest(clientUrl, authToken, clientInfoRequestDto, ClientInfoResponseDto.class);

    }

}
