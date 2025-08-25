package dev.guisandroni.warehouse.Service;

import dev.guisandroni.warehouse.Entity.ProductEntity;

import java.util.UUID;

public interface ProductService {
    ProductEntity save(final ProductEntity entity);
    ProductEntity findById (final UUID id);
    void purchase (final UUID id);
}
