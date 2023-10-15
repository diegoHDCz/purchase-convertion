package dev.diegoczajka.conversionpurchase.repository;

import dev.diegoczajka.conversionpurchase.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PurchaseRepository extends JpaRepository<Purchase, String> {
    Purchase findOneByid(UUID uuid);
}
