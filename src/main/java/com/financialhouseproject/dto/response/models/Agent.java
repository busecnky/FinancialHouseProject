package com.financialhouseproject.dto.response.models;

public record Agent(int id,
                    String customerIp,
                    String customerUserAgent,
                    String merchantIp) {
}
