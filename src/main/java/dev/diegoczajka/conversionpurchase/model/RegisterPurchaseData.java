package dev.diegoczajka.conversionpurchase.model;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record RegisterPurchaseData(
        @NotBlank(message = "Description is required")
        String description,

        @NotNull(message = "Transaction date is required")
        @PastOrPresent
        LocalDateTime transactionDate,

        @Positive(message = "Purchase amount must be a positive value")
        BigDecimal purchaseAmount
) {
}
