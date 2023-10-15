package dev.diegoczajka.conversionpurchase.service;

import dev.diegoczajka.conversionpurchase.model.PurchaseDetailData;
import dev.diegoczajka.conversionpurchase.model.RegisterPurchaseData;

import java.util.UUID;

public interface PurchaseService {

    PurchaseDetailData registerPurchase(RegisterPurchaseData data);

    PurchaseDetailData getOneById(UUID uuid);


}
