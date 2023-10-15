package dev.diegoczajka.conversionpurchase.entity;

import dev.diegoczajka.conversionpurchase.model.RegisterPurchaseData;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "purchase")
@Table(name = "purchases")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;


    private String description;


    private LocalDateTime transactionDate;


    private BigDecimal purchaseAmount;

    public Purchase(RegisterPurchaseData purchaseData) {

        this.id =  UUID.randomUUID();
        this.description = purchaseData.description();
        this.transactionDate = LocalDateTime.now();
        this.purchaseAmount =purchaseData. purchaseAmount();
    }

}
