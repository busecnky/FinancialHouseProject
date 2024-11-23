package com.financialhouseproject.dto.request;

import jakarta.validation.constraints.NotEmpty;

public record ClientInfoRequestDto(@NotEmpty(message = "Transaction Id field can not be empty")
                                   String transactionId) {
}
