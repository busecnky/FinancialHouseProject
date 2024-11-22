package com.financialhouseproject.controller;

import com.financialhouseproject.dto.request.TransactionListRequestDto;
import com.financialhouseproject.dto.request.TransactionRequestDto;
import com.financialhouseproject.dto.request.TransactionsReportRequestDto;
import com.financialhouseproject.dto.response.TransactionListResponseDto;
import com.financialhouseproject.dto.response.TransactionResponseDto;
import com.financialhouseproject.dto.response.TransactionsReportResponseDto;
import com.financialhouseproject.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class TransactionController {

    private final TransactionService transactionService;
    @PostMapping("/transactions/report")
    public ResponseEntity<TransactionsReportResponseDto> fetchTransactionsReport(
            @RequestHeader("Authorization") String authToken,
            @RequestBody TransactionsReportRequestDto transactionsReportRequestDto){
        return ResponseEntity.ok(transactionService.fetchTransactionsReport(transactionsReportRequestDto));

    }
    @PostMapping("/transaction/list")
    public ResponseEntity<TransactionListResponseDto> fetchTransactionList(
            @RequestHeader("Authorization") String authToken,
            @RequestBody TransactionListRequestDto transactionListRequestDto){
        return ResponseEntity.ok(transactionService.fetchTransactionList(transactionListRequestDto));

    }
    @PostMapping("/transaction")
    public ResponseEntity<TransactionResponseDto> fetchTransaction(
            @RequestHeader("Authorization") String authToken,
            @RequestBody TransactionRequestDto transactionRequestDto){
        return ResponseEntity.ok(transactionService.fetchTransaction(transactionRequestDto));

    }
}
