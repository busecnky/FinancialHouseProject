package com.financialhouseproject.dto.request.models;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Operation {
    DIRECT("DIRECT"),
    REFUND("REFUND"),
    THREE_D("3D"),
    THREE_D_AUTH("3DAUTH"),
    STORED("STORED");

    private final String value;

    Operation(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}


