package com.financialhouseproject.controller;

import com.financialhouseproject.dto.request.ClientInfoRequestDto;
import com.financialhouseproject.dto.response.ClientInfoResponseDto;
import com.financialhouseproject.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping()
@RestController
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;


    @PostMapping("/client")
    public ResponseEntity<ClientInfoResponseDto> fetchClientInfo(
            @RequestHeader("Authorization") String authToken,
            ClientInfoRequestDto clientInfoRequestDto){
        return ResponseEntity.ok(clientService.fetchClient(authToken,
                clientInfoRequestDto));
    }
}
