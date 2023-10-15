package dev.diegoczajka.conversionpurchase.model;

import dev.diegoczajka.conversionpurchase.entity.Purchase;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.UUID;

public record PurchaseDetailData(
        UUID id,
        String description,
        String transactionDate,
        BigDecimal purchaseAmount

) {
    public PurchaseDetailData(Purchase purchase, String dateTime) {
        this(purchase.getId(), purchase.getDescription(), dateTime, purchase.getPurchaseAmount());

    }
}
