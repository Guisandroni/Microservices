package dev.guisandroni.warehouse.Entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.*;

@Entity
@Getter
@Setter
@ToString
public class ProductEntity {
    @Id
    private UUID id;

    private String name;
    @ToString.Exclude
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<StockEntity> stocks = new HashSet<>();


    public StockEntity decStock(){
        var stock = this.stocks.stream().filter(s -> s.getStatus().equals(StockStatus.AVAILABLE))
                .min(Comparator.comparing(StockEntity::getSoldPrice))
                .orElseThrow();
        stock.decAmount();
        return stock;
    }
    @PrePersist
    private void prePersist(){
        this.id= UUID.randomUUID();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ProductEntity that = (ProductEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(stocks, that.stocks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, stocks);
    }
}
