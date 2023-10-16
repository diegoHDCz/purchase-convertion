package dev.diegoczajka.conversionpurchase.service;

import dev.diegoczajka.conversionpurchase.model.exchanges.ExchangeDetailData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
class ExchangeServiceTest {


    @Autowired
    private ExchangeService exchangeService;

    @Test
    @DisplayName("Should be able to call restTemplate without throwing excpetion")
    void getByCountry() {
       var result = assertDoesNotThrow(()-> exchangeService.getByCountry("Brazil","2023-06-30"));
        assertThat(result.getClass()).isEqualTo(ExchangeDetailData.class);
    }
}