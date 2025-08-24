package dev.guisandroni.store.Repository;

import dev.guisandroni.store.Entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {


    List<ProductEntity> findByActiveTrueOrderByNameAsc();
}
