package com.financialhouseproject.service;

import com.financialhouseproject.dto.request.TransactionListRequestDto;
import com.financialhouseproject.dto.request.models.TransactionListRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.financialhouseproject.dto.request.models.*;

import java.time.LocalDate;


class TransactionMapperTest {

    private TransactionMapper transactionMapper;

    @BeforeEach
    void setUp() {
        transactionMapper = new TransactionMapper();
    }

    @Test
    void testMapRequest() {
        TransactionListRequestDto requestDto = mock(TransactionListRequestDto.class);

        when(requestDto.getFromDate()).thenReturn(LocalDate.parse("2024-01-01"));
        when(requestDto.getToDate()).thenReturn(LocalDate.parse("2024-01-31"));
        when(requestDto.getStatus()).thenReturn(Status.APPROVED);
        when(requestDto.getOperation()).thenReturn(Operation.THREE_D);
        when(requestDto.getMerchantId()).thenReturn(123);
        when(requestDto.getAcquirerId()).thenReturn(456);
        when(requestDto.getPaymentMethod()).thenReturn(PaymentMethod.CREDITCARD);
        when(requestDto.getErrorCode()).thenReturn(ErrorCode.INVALID_CARD);
        when(requestDto.getFilterField()).thenReturn(FilterField.REFERENCE_NO);
        when(requestDto.getFilterValue()).thenReturn("12345");
        when(requestDto.getPage()).thenReturn(1);

        TransactionListRequest result = transactionMapper.mapRequest(requestDto);

        assertEquals(LocalDate.parse("2024-01-01"), result.getFromDate());
        assertEquals(LocalDate.parse("2024-01-31"), result.getToDate());
        assertEquals("APPROVED", result.getStatus());
        assertEquals("3D", result.getOperation());
        assertEquals(123, result.getMerchantId());
        assertEquals(456, result.getAcquirerId());
        assertEquals("CREDITCARD", result.getPaymentMethod());
        assertEquals("Invalid Card", result.getErrorCode());
        assertEquals("Reference No", result.getFilterField());
        assertEquals("12345", result.getFilterValue());
        assertEquals(1, result.getPage());
    }
}
