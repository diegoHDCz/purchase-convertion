package dev.diegoczajka.conversionpurchase.model.exchanges.validation;

import dev.diegoczajka.conversionpurchase.config.exception.ValidationException;
import dev.diegoczajka.conversionpurchase.entity.Purchase;
import dev.diegoczajka.conversionpurchase.model.exchanges.ExchangeDetailData;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

@Component
public class ValidateExchangeValidDate implements ValidatePurchaseExchange {
    @Override
    public void validate(ExchangeDetailData data, Purchase purchase) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.US);
        var exchangeDate = LocalDate.parse(data.record_date(), formatter);

        var differenceBetweenMonths = ChronoUnit.MONTHS.between(exchangeDate, purchase.getTransactionDate());
        if (differenceBetweenMonths >= 6) {
            throw new ValidationException("purchase cannot be converted to the target currency");
        }
    }

}
