package dev.diegoczajka.conversionpurchase.controller;

import dev.diegoczajka.conversionpurchase.model.RegisterPurchaseData;
import dev.diegoczajka.conversionpurchase.service.PurchaseService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@RestController
@Slf4j
public class PurchaseController {

    @Autowired
    PurchaseService service;

    @PostMapping
    public ResponseEntity register(@RequestBody @Valid RegisterPurchaseData data, UriComponentsBuilder uriBuilder) {
        var result = service.registerPurchase(data);

        var uri = uriBuilder.path("/{id}").buildAndExpand(result.id()).toUri();

        return ResponseEntity.created(uri).body(result);

    }

    @GetMapping("/{id}")
    public ResponseEntity detail(@PathVariable UUID id) {
        var purchase = service.getOneById(id);
        return ResponseEntity.ok(purchase);
    }
}
