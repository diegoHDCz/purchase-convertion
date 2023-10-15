package dev.diegoczajka.conversionpurchase.model.exchanges;

import java.math.BigDecimal;

public record ExchangeDetailData(
        String record_date,
        String country,
        String currency,
        String country_currency_desc,
        BigDecimal exchange_rate,
        String effective_date,
        int src_line_nbr,
        String record_fiscal_year,
        String record_fiscal_quarter,
        String record_calendar_year,
        String record_calendar_quarter,
        String record_calendar_month,
        String record_calendar_day


) {
}

