package dev.guisandroni.store.Service.Implements;

import dev.guisandroni.store.Dto.ProductInfoDto;
import dev.guisandroni.store.Entity.ProductEntity;
import dev.guisandroni.store.Repository.ProductRepository;
import dev.guisandroni.store.Service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository  ;

    @Override
    public ProductEntity save(ProductEntity entity) {
        return repository.save(entity);
    }

    @Override
    public void changeActivated(long id, boolean active) {

    }

    @Override
    public List<ProductEntity> findAllActive() {
        return List.of();
    }

    @Override
    public ProductInfoDto findInfo(long id) {
        return null;
    }

    @Override
    public void purchase(long id) {

    }
}
