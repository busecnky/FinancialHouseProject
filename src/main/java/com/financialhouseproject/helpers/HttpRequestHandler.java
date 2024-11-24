package com.financialhouseproject.helpers;

import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Component
public class HttpRequestHandler {

    private final RestTemplate restTemplate;

    public HttpRequestHandler(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public <T, R> R sendPostRequest(String url, String authToken, T requestDto, Class<R> responseType) {
        validateToken(url, authToken);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authToken);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<T> requestEntity = new HttpEntity<>(requestDto, headers);

        try {
            ResponseEntity<R> response = restTemplate.postForEntity(url, requestEntity, responseType);
            return Optional.ofNullable(response.getBody())
                    .orElseThrow(
                            () -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Empty response body"));
        } catch (HttpClientErrorException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "An error occurred during the request", e);
        }
    }

    private void validateToken(String url, String authToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authToken);
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        try {
            restTemplate.exchange(url, HttpMethod.HEAD, requestEntity, Void.class);
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid or expired token");
            }
        }
    }

}
