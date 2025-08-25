package dev.guisandroni.warehouse.Service.Impl;

import dev.guisandroni.warehouse.Entity.ProductEntity;
import dev.guisandroni.warehouse.Repository.ProductRepository;
import dev.guisandroni.warehouse.Service.ProductQueryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class ProductQueryServiceImpl implements ProductQueryService {
    private final ProductRepository productRepository;
    @Override
    public ProductEntity findById(UUID id) {
        return productRepository.findById(id).orElseThrow();
    }
}
