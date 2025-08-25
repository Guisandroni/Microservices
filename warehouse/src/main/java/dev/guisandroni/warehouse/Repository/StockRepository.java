package dev.guisandroni.warehouse.Repository;
import dev.guisandroni.warehouse.Entity.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StockRepository extends JpaRepository<StockEntity, UUID> {
}