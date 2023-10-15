package dev.diegoczajka.conversionpurchase.model.purchases;

import dev.diegoczajka.conversionpurchase.entity.Purchase;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record PurchaseDetailData(
        UUID id,
        String description,
        LocalDate transactionDate,
        BigDecimal purchaseAmount


) {
    public PurchaseDetailData(Purchase purchase) {
        this(purchase.getId(), purchase.getDescription(), purchase.getTransactionDate(), purchase.getPurchaseAmount().setScale(BigDecimal.ROUND_HALF_EVEN));

    }
}
