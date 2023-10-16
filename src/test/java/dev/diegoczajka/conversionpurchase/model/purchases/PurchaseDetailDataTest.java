package dev.diegoczajka.conversionpurchase.model.purchases;

import dev.diegoczajka.conversionpurchase.entity.Purchase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class PurchaseDetailDataTest {

    @Test
    void shouldCreateNewPurchasedetailData() {
        Purchase purchase = new Purchase();
        purchase.setPurchaseAmount(BigDecimal.ONE);
        purchase.setDescription("test");
        purchase.setId(UUID.randomUUID());
        purchase.setTransactionDate(LocalDate.now());
        var test = new PurchaseDetailData(purchase);
        Assertions.assertAll(
                () -> assertEquals(BigDecimal.ONE.setScale(BigDecimal.ROUND_HALF_EVEN), test.purchaseAmount()),
                () -> assertEquals("test", test.description()),
                () -> assertEquals(LocalDate.now(),test.transactionDate())
        );
    }

}