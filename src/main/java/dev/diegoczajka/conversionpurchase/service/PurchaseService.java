package dev.diegoczajka.conversionpurchase.service;

import dev.diegoczajka.conversionpurchase.model.exchanges.ExchangePurchaseDetailData;
import dev.diegoczajka.conversionpurchase.model.purchases.PurchaseDetailData;
import dev.diegoczajka.conversionpurchase.model.purchases.RegisterPurchaseData;

import java.util.UUID;

public interface PurchaseService {

    PurchaseDetailData registerPurchase(RegisterPurchaseData data);

    ExchangePurchaseDetailData getOneById(UUID uuid, String country);


}
