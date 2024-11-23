package com.financialhouseproject.dto.response.models;

public record TransactionMerchant(String referenceNo,
                                  String status,
                                  String operation,
                                  String message,
                                  String created_at,
                                  String transactionId) {
}
