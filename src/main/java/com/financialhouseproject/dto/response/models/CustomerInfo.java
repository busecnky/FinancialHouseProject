package com.financialhouseproject.dto.response.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record CustomerInfo(int id,
                           @JsonFormat(pattern = "yyyy-MM-dd")
                           LocalDate createdAt,
                           @JsonFormat(pattern = "yyyy-MM-dd")
                           LocalDate updatedAt,
                           @JsonFormat(pattern = "yyyy-MM-dd")
                           LocalDate deletedAt,
                           String number,
                           String expiryMonth,
                           String expiryYear,
                           String startMonth,
                           String startYear,
                           String issueNumber,
                           String email,
                           @JsonFormat(pattern = "yyyy-MM-dd")
                           LocalDate birthday,
                           String gender,
                           String billingTitle,
                           String billingFirstName,
                           String billingLastName,
                           String billingCompany,
                           String billingAddress1,
                           String billingAddress2,
                           String billingCity,
                           String billingPostcode,
                           String billingState,
                           String billingCountry,
                           String billingPhone,
                           String billingFax,
                           String shippingTitle,
                           String shippingFirstName,
                           String shippingLastName,
                           String shippingCompany,
                           String shippingAddress1,
                           String shippingAddress2,
                           String shippingCity,
                           String shippingPostcode,
                           String shippingState,
                           String shippingCountry,
                           String shippingPhone,
                           String shippingFax
) {
}
