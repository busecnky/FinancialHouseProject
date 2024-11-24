package com.financialhouseproject.dto.request.models;

import com.fasterxml.jackson.annotation.JsonValue;

public enum PaymentMethod {
    CREDITCARD("CREDITCARD"),
    CUP("CUP"),
    IDEAL("IDEAL"),
    GIROPAY("GIROPAY"),
    MISTERCASH("MISTERCASH"),
    STORED("STORED"),
    PAYTOCARD("PAYTOCARD"),
    CEPBANK("CEPBANK"),
    CITADEL("CITADEL");

    private final String value;

    PaymentMethod(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
