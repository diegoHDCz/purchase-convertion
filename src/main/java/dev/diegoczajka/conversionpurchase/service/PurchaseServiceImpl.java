package dev.diegoczajka.conversionpurchase.service;

import dev.diegoczajka.conversionpurchase.entity.Purchase;
import dev.diegoczajka.conversionpurchase.model.exchanges.ExchangePurchaseDetailData;
import dev.diegoczajka.conversionpurchase.model.exchanges.validation.ValidatePurchaseExchange;
import dev.diegoczajka.conversionpurchase.model.purchases.PurchaseDetailData;
import dev.diegoczajka.conversionpurchase.model.purchases.RegisterPurchaseData;
import dev.diegoczajka.conversionpurchase.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.UUID;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    PurchaseRepository purchaseRepository;

    @Autowired
    ExchangeService exchangeService;

    @Autowired
    ValidatePurchaseExchange validator;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.US);

    @Override
    public PurchaseDetailData registerPurchase(RegisterPurchaseData data) {
        Purchase purchase = new Purchase(data);
        purchaseRepository.save(purchase);
        return new PurchaseDetailData(purchase);

    }

    @Override
    public ExchangePurchaseDetailData getOneById(UUID uuid, String country) {

        var purchase = purchaseRepository.findOneByid(uuid);


        var exchangeRate = exchangeService.getByCountry(country, purchase.getTransactionDate().format(formatter));
        validator.validate(exchangeRate, purchase);


        return new ExchangePurchaseDetailData(purchase,exchangeRate);
    }


}
