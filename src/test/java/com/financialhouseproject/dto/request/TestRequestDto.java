package com.financialhouseproject.dto.request;

public class TestRequestDto {
    private String data;

    public TestRequestDto(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
