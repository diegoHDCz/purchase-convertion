package dev.diegoczajka.conversionpurchase.model.exchanges;

import dev.diegoczajka.conversionpurchase.entity.Purchase;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record ExchangePurchaseDetailData(
        UUID id,
        String description,
        LocalDate transactionDate,
        BigDecimal purchaseAmountUS,
        BigDecimal exchangeRate,
        BigDecimal convertedAmount
) {
    public ExchangePurchaseDetailData(Purchase purchase, ExchangeDetailData exchange) {
        this(purchase.getId(),
                purchase.getDescription(),
                purchase.getTransactionDate(),
                purchase.getPurchaseAmount(),
                exchange.exchange_rate(),
                purchase.getPurchaseAmount().multiply(
                        exchange.exchange_rate().setScale(BigDecimal.ROUND_HALF_EVEN)));
    }
}
