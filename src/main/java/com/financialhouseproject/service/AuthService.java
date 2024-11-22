package com.financialhouseproject.service;

import com.financialhouseproject.dto.request.AuthRequestDto;
import com.financialhouseproject.dto.response.AuthResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {
    private final RestTemplate restTemplate;
    @Value("${baseUrl}/merchant/user/login")
    private String loginURL;

    public AuthService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public AuthResponseDto authenticate(AuthRequestDto authRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("email", authRequest.getEmail());
        requestBody.put("password", authRequest.getPassword());

        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);
        try {
            ResponseEntity<AuthResponseDto> response = restTemplate.postForEntity(loginURL, requestEntity, AuthResponseDto.class);
            return response.getBody();
        } catch (HttpClientErrorException e) {
            return e.getResponseBodyAs(AuthResponseDto.class);
        }
    }
}
