package com.financialhouseproject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.financialhouseproject.dto.request.ClientInfoRequestDto;
import com.financialhouseproject.dto.response.ClientInfoResponseDto;
import com.financialhouseproject.dto.response.models.CustomerInfo;
import com.financialhouseproject.service.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ClientController.class)
public class ClientControllerTest {
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @MockBean
    private ClientService clientService;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testFetchClient() throws Exception {
        String authToken = "token";
        ClientInfoRequestDto request = new ClientInfoRequestDto("transactionId");
        CustomerInfo customerInfo = new CustomerInfo(1, LocalDate.now(),LocalDate.now(),
                LocalDate.now(),"number","","","","","",
                "",LocalDate.of(1980, 11, 24),"","","",
                "","","","","","",
                "","","","","","",
                "","","","","",
                "","","","","");
        ClientInfoResponseDto response = new ClientInfoResponseDto(customerInfo);

        Mockito.when(clientService.fetchClient(authToken,request)).thenReturn(response);

        mockMvc.perform(post("/client")
                        .header("Authorization", authToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper().writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerInfo.id").value(1))
                .andExpect(jsonPath("$.customerInfo.number").value("number"))
                .andExpect(jsonPath("$.customerInfo.birthday").value("1980-11-24"));

        Mockito.verify(clientService,Mockito.times(1)).fetchClient(authToken,request);
    }


    public ObjectMapper objectMapper() {
        return JsonMapper.builder()
                .findAndAddModules()
                .build();
    }
}
