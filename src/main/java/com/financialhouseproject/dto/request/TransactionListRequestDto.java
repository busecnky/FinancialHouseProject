package com.financialhouseproject.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record TransactionListRequestDto(@JsonFormat(pattern = "yyyy-MM-dd")
                                        LocalDate fromDate,
                                        @JsonFormat(pattern = "yyyy-MM-dd")
                                        LocalDate toDate,
                                        String status,
                                        String operation,
                                        Integer merchantId,
                                        Integer acquirerId,
                                        String paymentMethod,
                                        String errorCode,
                                        String filterField,
                                        String filterValue,
                                        Integer page) {}
