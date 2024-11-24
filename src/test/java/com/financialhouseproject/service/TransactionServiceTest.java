package com.financialhouseproject.service;

import com.financialhouseproject.dto.request.TransactionListRequestDto;
import com.financialhouseproject.dto.request.TransactionRequestDto;
import com.financialhouseproject.dto.request.TransactionsReportRequestDto;
import com.financialhouseproject.dto.request.models.TransactionListRequest;
import com.financialhouseproject.dto.response.TransactionListResponseDto;
import com.financialhouseproject.dto.response.TransactionResponseDto;
import com.financialhouseproject.dto.response.TransactionsReportResponseDto;
import com.financialhouseproject.dto.response.models.*;
import com.financialhouseproject.helpers.HttpRequestHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

    @Mock
    private HttpRequestHandler httpRequestHandler;

    @Mock
    private TransactionMapper transactionMapper;
    @Mock
    private FxInformation fx;
    @Mock
    private CustomerInfo customerInfo;
    @Mock
    private MerchantName merchant;
    @Mock
    private TransactionInfo transaction;

    @InjectMocks
    private TransactionService transactionService;

    @Value("${baseUrl}/transactions/report")
    private String reportUrl;

    @Value("${baseUrl}/transaction/list")
    private String listUrl;

    @Value("${baseUrl}/transaction")
    private String transactionUrl;

    @Test
    public void testFetchTransactionsReport_Success() {
        String authToken = "token";
        TransactionsReportRequestDto requestDto = new TransactionsReportRequestDto(
                LocalDate.now(),LocalDate.now(),
                0,0);
        TransactionsReportResponseDto expectedResponse = new TransactionsReportResponseDto("status", List.of());

        Mockito.when(httpRequestHandler.sendPostRequest(
                        reportUrl,
                        authToken,
                        requestDto,
                        TransactionsReportResponseDto.class))
                .thenReturn(expectedResponse);

        TransactionsReportResponseDto actualResponse = transactionService.fetchTransactionsReport(authToken, requestDto);

        Assertions.assertNotNull(actualResponse);
        Assertions.assertEquals(expectedResponse, actualResponse);

        Mockito.verify(httpRequestHandler, Mockito.times(1))
                .sendPostRequest(reportUrl, authToken, requestDto,
                        TransactionsReportResponseDto.class);
    }

    @Test
    public void testFetchTransactionList_Success() {
        String authToken = "token";
        TransactionListRequestDto requestDto = new TransactionListRequestDto();
        TransactionListRequest mappedRequest = new TransactionListRequest(LocalDate.now(), LocalDate.now(),
                "status", "operation",
                0,0,
                "paymentMethod","errorCode",
                 "filterField","filterValue",0 );
        TransactionListResponseDto expectedResponse = new TransactionListResponseDto(0,0,
                "next_page_url", "prev_page_url",
               0,0, List.of());

        Mockito.when(transactionMapper.mapRequest(requestDto)).thenReturn(mappedRequest);
        Mockito.when(httpRequestHandler.sendPostRequest(
                        listUrl,
                        authToken,
                        mappedRequest,
                        TransactionListResponseDto.class))
                .thenReturn(expectedResponse);

        TransactionListResponseDto actualResponse = transactionService.fetchTransactionList(authToken, requestDto);

        Assertions.assertNotNull(actualResponse);
        Assertions.assertEquals(expectedResponse, actualResponse);

        Mockito.verify(transactionMapper, Mockito.times(1)).mapRequest(Mockito.eq(requestDto));
        Mockito.verify(httpRequestHandler, Mockito.times(1))
                .sendPostRequest(listUrl, authToken, mappedRequest,
                        TransactionListResponseDto.class);
    }

    @Test
    public void testFetchTransaction_Success() {
        String authToken = "token";
        TransactionRequestDto requestDto = new TransactionRequestDto("transactionId");
        TransactionResponseDto expectedResponse = new TransactionResponseDto(fx,customerInfo,merchant,transaction);

        Mockito.when(httpRequestHandler.sendPostRequest(
                        transactionUrl,
                        authToken,
                        requestDto,
                        TransactionResponseDto.class))
                .thenReturn(expectedResponse);

        TransactionResponseDto actualResponse = transactionService.fetchTransaction(authToken, requestDto);

        Assertions.assertNotNull(actualResponse);
        Assertions.assertEquals(expectedResponse, actualResponse);

        Mockito.verify(httpRequestHandler, Mockito.times(1))
                .sendPostRequest(transactionUrl, authToken, requestDto,
                        TransactionResponseDto.class);
    }
}
