package com.financialhouseproject.dto.response.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record TransactionInfoMerchant(String referenceNo,
                                      int merchantId,
                                      String status,
                                      String channel,
                                      String customData,
                                      String chainId,
                                      int agentInfoId,
                                      String operation,
                                      int fxTransactionId,
                                      @JsonFormat(pattern = "yyyy-MM-dd")
                                      LocalDate updatedAt,
                                      @JsonFormat(pattern = "yyyy-MM-dd")
                                      LocalDate createdAt,
                                      int id,
                                      int acquirerTransactionId,
                                      String code,
                                      String message,
                                      String transactionId,
                                      Agent agent) {
}
