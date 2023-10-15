package dev.diegoczajka.conversionpurchase.service;

import dev.diegoczajka.conversionpurchase.model.exchanges.ExchangeDetailData;

public interface ExchangeService {
    public ExchangeDetailData getByCountry(String country,String dateOfPurchase);

}
