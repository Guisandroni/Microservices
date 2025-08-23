package dev.guisandroni.store.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;


@Entity
@Getter
@Setter
@ToString
public class Product {


    @Id
    private Long id;
    private String name;
    private Boolean active;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) && Objects.equals(name, product.name) && Objects.equals(active, product.active);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, active);
    }
}
