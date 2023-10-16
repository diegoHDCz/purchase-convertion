package dev.diegoczajka.conversionpurchase.model.exchanges.validation;

import dev.diegoczajka.conversionpurchase.config.exception.ValidationException;
import dev.diegoczajka.conversionpurchase.entity.Purchase;
import dev.diegoczajka.conversionpurchase.model.exchanges.ExchangeDetailData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ValidatePurchaseExchangeTest {
    @Autowired
    private ValidatePurchaseExchange validation;

    @Test
    @DisplayName("Should be able to validate and throw excpeiton if part months is over 6 months")
    void validateException() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.US);
        ExchangeDetailData exchangeDetailData = new ExchangeDetailData("2021-06-10",
                "brazil",
                "real", "real",
                BigDecimal.ONE, "effective",
                2, "2021", "3",
                "2021", "3", "06", "10");
        Purchase purchase = new Purchase();
        purchase.setTransactionDate(LocalDate.parse("2023-06-10", formatter));
        assertThrows(ValidationException.class,()-> validation.validate(exchangeDetailData,purchase));
    }
}

