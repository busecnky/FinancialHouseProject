package com.financialhouseproject.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.financialhouseproject.dto.request.models.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionListRequestDto{
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fromDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate toDate;
    private Status status;
    private Operation operation;
    private Integer merchantId;
    private Integer acquirerId;
    private PaymentMethod paymentMethod;
    private ErrorCode errorCode;
    private FilterField filterField;
    private String filterValue;
    private Integer page;
}
