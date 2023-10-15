package dev.diegoczajka.conversionpurchase.model.purchases;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

public record RegisterPurchaseData(
        @NotBlank(message = "Description is required")
        String description,

        @NotNull(message = "Transaction date is required")
        @PastOrPresent(message = "must be past or present date")
        LocalDate transactionDate,

        @Positive(message = "Purchase amount must be a positive value")
        BigDecimal purchaseAmount
) {
}
