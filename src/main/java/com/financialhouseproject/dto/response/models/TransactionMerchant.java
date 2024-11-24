package com.financialhouseproject.dto.response.models;

import java.time.LocalDate;

public record TransactionMerchant(String referenceNo,
                                  String status,
                                  String operation,
                                  String message,
                                  LocalDate created_at,
                                  String transactionId) {
}
