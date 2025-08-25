package dev.guisandroni.warehouse.Entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
public class StockEntity {

    @Id
    private UUID id;

    private  Long amount;
    private BigDecimal boughtPrice;
    @Enumerated(EnumType.STRING)
    private StockStatus status;

    private BigDecimal soldPrice;


    @ToString.Exclude
    @ManyToOne
    @JoinColumn (name = "product_id", nullable = false)
    private ProductEntity product;

    public void decAmount (){
        this.amount -= 1;
        if(this.amount == 0){
            this.status = StockStatus.UNAVAILABLE;
        }
    }

    public boolean isNotAvailable(){
        return this.status == StockStatus.UNAVAILABLE;
    }

    @PrePersist
    private void prePersist(){
        this.id= UUID.randomUUID();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        StockEntity that = (StockEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(amount, that.amount) && Objects.equals(boughtPrice, that.boughtPrice) && status == that.status && Objects.equals(soldPrice, that.soldPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, boughtPrice, status, soldPrice);
    }
}
