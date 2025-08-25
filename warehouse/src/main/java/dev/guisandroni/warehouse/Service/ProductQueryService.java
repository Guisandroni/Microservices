package dev.guisandroni.warehouse.Service;

import dev.guisandroni.warehouse.Entity.ProductEntity;

import java.util.UUID;

public interface ProductQueryService {
    ProductEntity findById(final UUID id);
}
