package com.financialhouseproject.dto.response;

import com.financialhouseproject.dto.response.models.FxInformation;
import com.financialhouseproject.dto.response.models.MerchantName;
import com.financialhouseproject.dto.response.models.CustomerInfo;
import com.financialhouseproject.dto.response.models.TransactionInfo;

public record TransactionResponseDto(FxInformation fx,
                                     CustomerInfo customerInfo,
                                     MerchantName merchant,
                                     TransactionInfo transaction
                                     ) {
}
