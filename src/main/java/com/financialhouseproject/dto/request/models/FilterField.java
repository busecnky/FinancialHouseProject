package com.financialhouseproject.dto.request.models;

import com.fasterxml.jackson.annotation.JsonValue;

public enum FilterField {
    TRANSACTION_UUID("Transaction UUID"),
    CUSTOMER_EMAIL("Customer Email"),
    REFERENCE_NO("Reference No"),
    CUSTOM_DATA("Custom Data"),
    CARD_PAN("Card PAN");

    private final String value;

    FilterField(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}