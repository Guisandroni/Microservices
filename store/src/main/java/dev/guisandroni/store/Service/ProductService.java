package dev.guisandroni.store.Service;

import dev.guisandroni.store.Dto.ProductInfoDto;
import dev.guisandroni.store.Entity.ProductEntity;

import java.util.List;

public interface ProductService {
    ProductEntity save(final ProductEntity entity);

    void changeActivated(final long id, final boolean active);

    List<ProductEntity> findAllActive();

    ProductInfoDto findInfo(final long id);

    void purchase(final long id);


}
