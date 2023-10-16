package dev.diegoczajka.conversionpurchase.controller;

import dev.diegoczajka.conversionpurchase.model.exchanges.ExchangePurchaseDetailData;
import dev.diegoczajka.conversionpurchase.model.purchases.PurchaseDetailData;
import dev.diegoczajka.conversionpurchase.model.purchases.RegisterPurchaseData;
import dev.diegoczajka.conversionpurchase.service.PurchaseService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class PurchaseControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<RegisterPurchaseData> registerPurchaseDataJson;


    @MockBean
    PurchaseService service;


    @Test
    @DisplayName("Show throw excpetion for invalid register data")
    void registerError() throws Exception {
        var response = mvc.perform(post("/purchase")).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Show be able to create purchase")
    void register() throws Exception {

        var result = new PurchaseDetailData(UUID.randomUUID(), "test", LocalDate.now(), BigDecimal.ONE);
        when(service.registerPurchase(any())).thenReturn(result);

        var response = mvc.perform(post("/purchase")
                .contentType(MediaType.APPLICATION_JSON).content(
                        registerPurchaseDataJson.write(
                                new RegisterPurchaseData("teste", LocalDate.now(), BigDecimal.ONE)
                        ).getJson()
                )).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());

    }

    @Test
    @DisplayName("Should throw excpetion that user does not exists")
    void detailExcpetion() throws Exception {
        var response = mvc.perform(get("/purchase/", UUID.randomUUID(), "brazil")).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    @Test
    @DisplayName("Should be able to get a register")
    void detail() throws Exception {
        ExchangePurchaseDetailData result = new ExchangePurchaseDetailData(UUID.randomUUID(), "test",
                LocalDate.now(), BigDecimal.ONE, BigDecimal.TEN, BigDecimal.ONE.multiply(BigDecimal.TEN));
        when(service.getOneById(any(), any())).thenReturn(result);


                var response = mvc.perform(get("/purchase/" + UUID.randomUUID() + "/brazil")).andReturn().getResponse();


        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }
}

