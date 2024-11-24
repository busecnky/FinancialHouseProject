package com.financialhouseproject.service;

import com.financialhouseproject.dto.request.TransactionListRequestDto;
import com.financialhouseproject.dto.request.models.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransactionMapper {

    public TransactionListRequest mapRequest(TransactionListRequestDto transactionListRequestDto) {
        return new TransactionListRequest(
                transactionListRequestDto.getFromDate(),
                transactionListRequestDto.getToDate(),
                mapNullableEnum(transactionListRequestDto.getStatus()),
                mapNullableEnum(transactionListRequestDto.getOperation()),
                transactionListRequestDto.getMerchantId(),
                transactionListRequestDto.getAcquirerId(),
                mapNullableEnum(transactionListRequestDto.getPaymentMethod()),
                mapNullableEnum(transactionListRequestDto.getErrorCode()),
                mapNullableEnum(transactionListRequestDto.getFilterField()),
                transactionListRequestDto.getFilterValue(),
                transactionListRequestDto.getPage()
        );
    }

    private <T extends Enum<T>> String mapNullableEnum(T enumValue) {
        return Optional.ofNullable(enumValue)
                .map(value -> {
                    try {
                        return value.getClass().getMethod("getValue").invoke(value).toString();
                    } catch (Exception e) {
                        return value.name();
                    }
                })
                .orElse(null);
    }
}
