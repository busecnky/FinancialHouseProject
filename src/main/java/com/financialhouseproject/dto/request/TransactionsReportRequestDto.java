package com.financialhouseproject.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record TransactionsReportRequestDto(@NotNull(message = "fromDate field cannot be null.")
                                           @JsonFormat(pattern = "yyyy-MM-dd")
                                           LocalDate fromDate,
                                           @NotNull(message = "toDate field cannot be null.")
                                           @JsonFormat(pattern = "yyyy-MM-dd")
                                           LocalDate toDate,
                                           int merchant,
                                           int acquirer) {
}
