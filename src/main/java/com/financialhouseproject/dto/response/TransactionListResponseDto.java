package com.financialhouseproject.dto.response;

import com.financialhouseproject.dto.response.models.ListResponseData;

import java.util.List;

public record TransactionListResponseDto(Integer per_page,
                                         Integer current_page,
                                         String next_page_url,
                                         String prev_page_url,
                                         Integer from,
                                         Integer to,
                                         List<ListResponseData> data) {

}
