package com.financialhouseproject.dto.response;


import com.financialhouseproject.dto.response.models.ReportResponse;

import java.util.List;

public record TransactionsReportResponseDto(String status,
                                            List<ReportResponse> response) {}
