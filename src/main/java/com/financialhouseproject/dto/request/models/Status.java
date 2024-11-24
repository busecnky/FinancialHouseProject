package com.financialhouseproject.dto.request.models;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Status {
    APPROVED("APPROVED"),
    WAITING("WAITING"),
    DECLINED("DECLINED"),
    ERROR("ERROR");

    private final String value;


    Status(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
