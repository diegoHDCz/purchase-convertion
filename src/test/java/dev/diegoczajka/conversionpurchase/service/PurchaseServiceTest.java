package dev.diegoczajka.conversionpurchase.service;

import dev.diegoczajka.conversionpurchase.config.exception.ValidationException;
import dev.diegoczajka.conversionpurchase.entity.Purchase;
import dev.diegoczajka.conversionpurchase.model.exchanges.ExchangeDetailData;
import dev.diegoczajka.conversionpurchase.model.exchanges.validation.ValidatePurchaseExchange;
import dev.diegoczajka.conversionpurchase.model.purchases.RegisterPurchaseData;
import dev.diegoczajka.conversionpurchase.repository.PurchaseRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@SpringBootTest
class PurchaseServiceTest {

    @Autowired
    PurchaseService service;
    @MockBean
    PurchaseRepository purchaseRepository;

    @MockBean
    ExchangeService exchangeService;

    @MockBean
    ValidatePurchaseExchange validator;

    RegisterPurchaseData req;

    @BeforeEach
    void setUp() {
        req = new RegisterPurchaseData("teste", LocalDate.now(), BigDecimal.TEN);

    }

    @Test
    @DisplayName("should be able to register purchase")
    void registerPurchase() {


        var result = service.registerPurchase(req);
        assertThat(result.description()).isEqualTo("teste");
    }

    @Test
    @DisplayName("Should be able get purchase and exchange")
    void getOneById() {
        Purchase purchase = new Purchase();
        purchase.setTransactionDate(LocalDate.now());
        purchase.setPurchaseAmount(BigDecimal.TEN);
        purchase.setDescription("test");
        purchase.setId(UUID.randomUUID());
        Mockito.when(purchaseRepository.findOneByid(any())).thenReturn(purchase);

        ExchangeDetailData exchangeDetailData = new ExchangeDetailData("2023-09-10",
                "brazil",
                "real", "real",
                BigDecimal.TEN, "effective",
                2, "2021", "3",
                "2021", "3", "06", "10");

        Mockito.when(exchangeService.getByCountry(anyString(), anyString())).thenReturn(exchangeDetailData);

        var result = service.getOneById(UUID.randomUUID(), "Brazil");

        assertThat(result.purchaseAmountUS()).isEqualTo(BigDecimal.TEN);
        assertThat(result.convertedAmount()).
                isEqualTo(BigDecimal.TEN.multiply(BigDecimal.TEN).setScale(BigDecimal.ROUND_HALF_EVEN));

    }
}