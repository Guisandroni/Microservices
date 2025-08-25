package dev.guisandroni.warehouse.Service;

import dev.guisandroni.warehouse.Entity.StockEntity;
import dev.guisandroni.warehouse.Entity.StockStatus;

import java.util.UUID;

public interface StockService {

    StockEntity save(final StockEntity stock);
    void released(final UUID id);
    void inactive(final UUID id);
    void changeStatus(final UUID id, final StockStatus status);
}
