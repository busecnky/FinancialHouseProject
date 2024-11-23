package com.financialhouseproject.service;

import com.financialhouseproject.dto.request.TransactionListRequestDto;
import com.financialhouseproject.dto.request.TransactionRequestDto;
import com.financialhouseproject.dto.request.TransactionsReportRequestDto;
import com.financialhouseproject.dto.response.TransactionListResponseDto;
import com.financialhouseproject.dto.response.TransactionResponseDto;
import com.financialhouseproject.dto.response.TransactionsReportResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    private final HttpRequestHandler httpRequestHandler;
    @Value("${baseUrl}/transactions/report")
    private String reportUrl;
    @Value("${baseUrl}/transaction/list")
    private String listUrl;
    @Value("${baseUrl}/transaction")
    private String transactionUrl;

    public TransactionService(HttpRequestHandler httpRequestHandler) {
        this.httpRequestHandler = httpRequestHandler;
    }

    public TransactionsReportResponseDto fetchTransactionsReport(
            String authToken,
            TransactionsReportRequestDto transactionsReportRequestDto) {
        return httpRequestHandler.sendPostRequest(reportUrl, authToken, transactionsReportRequestDto, TransactionsReportResponseDto.class);

    }


    public TransactionListResponseDto fetchTransactionList(
            String authToken,
            TransactionListRequestDto transactionListRequestDto) {
        return httpRequestHandler.sendPostRequest(listUrl, authToken, transactionListRequestDto, TransactionListResponseDto.class);

    }

    public TransactionResponseDto fetchTransaction(String authToken, TransactionRequestDto transactionRequestDto) {
        return httpRequestHandler.sendPostRequest(transactionUrl, authToken, transactionRequestDto, TransactionResponseDto.class);
    }
}
