package com.financialhouseproject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.financialhouseproject.dto.request.TransactionListRequestDto;
import com.financialhouseproject.dto.request.TransactionRequestDto;
import com.financialhouseproject.dto.request.TransactionsReportRequestDto;
import com.financialhouseproject.dto.response.TransactionListResponseDto;
import com.financialhouseproject.dto.response.TransactionResponseDto;
import com.financialhouseproject.dto.response.TransactionsReportResponseDto;
import com.financialhouseproject.dto.response.models.CustomerInfo;
import com.financialhouseproject.dto.response.models.FxInformation;
import com.financialhouseproject.dto.response.models.MerchantName;
import com.financialhouseproject.dto.response.models.TransactionInfo;
import com.financialhouseproject.service.TransactionService;
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
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = TransactionController.class)
public class TransactionControllerTest {

    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @MockBean
    private TransactionService transactionService;
    @MockBean
    private FxInformation fx;
    @MockBean
    private CustomerInfo customerInfo;
    @MockBean
    private TransactionInfo transaction;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


    @Test
    public void testFetchTransactionsReport() throws Exception {
        String authToken = "test-token";
        TransactionsReportRequestDto request = new TransactionsReportRequestDto(
                LocalDate.of(2024, 11, 24),
                LocalDate.of(2024, 11, 25),
                0,
                0
        );
        TransactionsReportResponseDto response = new TransactionsReportResponseDto(
                "status",
                List.of()
        );

        Mockito.when(transactionService.fetchTransactionsReport(authToken, request)).thenReturn(response);

        mockMvc.perform(post("/transactions/report")
                        .header("Authorization", authToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper().writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("status"));


        Mockito.verify(transactionService, Mockito.times(1)).fetchTransactionsReport(authToken, request);
    }

    @Test
    public void testFetchTransactionList() throws Exception {
        String authToken = "token";
        TransactionListRequestDto request = new TransactionListRequestDto();
        TransactionListResponseDto response = new TransactionListResponseDto(10,1,
                "next_page_url", "prev_page_url", 0,0, List.of());

        Mockito.when(transactionService.fetchTransactionList(authToken, request)).thenReturn(response);

        mockMvc.perform(post("/transaction/list")
                        .header("Authorization", authToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper().writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.per_page").value(10))
                .andExpect(jsonPath("$.current_page").value(1))
                .andExpect(jsonPath("$.next_page_url").value("next_page_url"))
                .andExpect(jsonPath("$.prev_page_url").value("prev_page_url"));


        Mockito.verify(transactionService, Mockito.times(1)).fetchTransactionList(authToken, request);
    }

    @Test
    public void testFetchTransaction() throws Exception {
        String authToken = "token";
        TransactionRequestDto request = new TransactionRequestDto("transactionId");
        MerchantName merchant = new MerchantName("merchantName");
        TransactionResponseDto response = new TransactionResponseDto(fx,customerInfo, merchant, transaction);

        Mockito.when(transactionService.fetchTransaction(authToken, request)).thenReturn(response);

        mockMvc.perform(post("/transaction")
                        .header("Authorization", authToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper().writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.merchant.name").value("merchantName"));

        Mockito.verify(transactionService, Mockito.times(1)).fetchTransaction(authToken, request);
    }

    public ObjectMapper objectMapper() {
        return JsonMapper.builder()
                .findAndAddModules()
                .build();
    }
}


