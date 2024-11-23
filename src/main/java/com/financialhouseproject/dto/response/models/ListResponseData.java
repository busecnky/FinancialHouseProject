package com.financialhouseproject.dto.response.models;

public record ListResponseData(FxInformation fx,
                               ListCustomerInfo customerInfo,
                               MerchantInfo merchant,
                               IpnInfo ipn,
                               TransactionListInfo transaction,
                               AcquirerInfo acquirer,
                               Boolean refundable
                               ) {
}
