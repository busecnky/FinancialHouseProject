package com.financialhouseproject.service;

import com.financialhouseproject.dto.request.TransactionListRequestDto;
import com.financialhouseproject.dto.request.TransactionRequestDto;
import com.financialhouseproject.dto.request.TransactionsReportRequestDto;
import com.financialhouseproject.dto.request.models.TransactionListRequest;
import com.financialhouseproject.dto.response.TransactionListResponseDto;
import com.financialhouseproject.dto.response.TransactionResponseDto;
import com.financialhouseproject.dto.response.TransactionsReportResponseDto;
import com.financialhouseproject.helpers.HttpRequestHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    private final HttpRequestHandler httpRequestHandler;
    private final TransactionMapper transactionMapper;
    @Value("${baseUrl}/transactions/report")
    private String reportUrl;
    @Value("${baseUrl}/transaction/list")
    private String listUrl;
    @Value("${baseUrl}/transaction")
    private String transactionUrl;

    public TransactionService(HttpRequestHandler httpRequestHandler, TransactionMapper transactionMapper) {
        this.httpRequestHandler = httpRequestHandler;
        this.transactionMapper = transactionMapper;
    }

    public TransactionsReportResponseDto fetchTransactionsReport(
            String authToken,
            TransactionsReportRequestDto transactionsReportRequestDto) {
        return httpRequestHandler.sendPostRequest(reportUrl, authToken,
                transactionsReportRequestDto, TransactionsReportResponseDto.class);

    }


    public TransactionListResponseDto fetchTransactionList(
            String authToken,
            TransactionListRequestDto transactionListRequestDto) {
        TransactionListRequest transactionListRequest = transactionMapper.
                mapRequest(transactionListRequestDto);
        return httpRequestHandler.sendPostRequest(listUrl, authToken,
                transactionListRequest,
                TransactionListResponseDto.class);

    }

    public TransactionResponseDto fetchTransaction(String authToken, TransactionRequestDto transactionRequestDto) {
        return httpRequestHandler.sendPostRequest(transactionUrl, authToken,
                transactionRequestDto, TransactionResponseDto.class);
    }
}
