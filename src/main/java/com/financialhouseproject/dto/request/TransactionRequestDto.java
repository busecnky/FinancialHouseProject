package com.financialhouseproject.dto.request;

import jakarta.validation.constraints.NotEmpty;

public record TransactionRequestDto(@NotEmpty(message = "Transaction Id field can not be empty")
                                    String transactionId) {
}
