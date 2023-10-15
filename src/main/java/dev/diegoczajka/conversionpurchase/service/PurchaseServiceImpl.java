package dev.diegoczajka.conversionpurchase.service;

import dev.diegoczajka.conversionpurchase.entity.Purchase;
import dev.diegoczajka.conversionpurchase.model.PurchaseDetailData;
import dev.diegoczajka.conversionpurchase.model.RegisterPurchaseData;
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

    private   DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.US);

    @Override
    public PurchaseDetailData registerPurchase(RegisterPurchaseData data) {
        Purchase purchase = new Purchase(data);
        purchaseRepository.save(purchase);
        return new PurchaseDetailData(purchase, purchase.getTransactionDate().format(formatter));

    }

    @Override
    public PurchaseDetailData getOneById(UUID uuid) {

        var purchase = purchaseRepository.findOneByid(uuid);
        return new PurchaseDetailData(purchase, purchase.getTransactionDate().format(formatter));
    }

}
