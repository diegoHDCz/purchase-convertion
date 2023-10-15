package dev.diegoczajka.conversionpurchase.model.exchanges.validation;

import dev.diegoczajka.conversionpurchase.entity.Purchase;
import dev.diegoczajka.conversionpurchase.model.exchanges.ExchangeDetailData;

public interface ValidatePurchaseExchange {
    void validate(ExchangeDetailData data, Purchase purchase);
}
