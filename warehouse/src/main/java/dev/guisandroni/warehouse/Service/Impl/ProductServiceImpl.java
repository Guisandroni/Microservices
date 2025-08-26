package dev.guisandroni.warehouse.Service.Impl;

import dev.guisandroni.warehouse.Dto.ProductStoreSaveDto;
import dev.guisandroni.warehouse.Entity.ProductEntity;
import dev.guisandroni.warehouse.Mapper.ProductMapper;
import dev.guisandroni.warehouse.Repository.ProductRepository;
import dev.guisandroni.warehouse.Service.ProductQueryService;
import dev.guisandroni.warehouse.Service.ProductService;
import dev.guisandroni.warehouse.Service.StockService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.UUID;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {


    private final ProductRepository productRepository;
    private final ProductQueryService productService;
    private final StockService stockService;
    private final RestClient storeClient;
    private final ProductMapper mapper;


    @Override
    public ProductEntity save(final ProductEntity product) {
        productRepository.save(product);
        var dto = mapper.toDTO(product);
        saveStore(dto);
        return product;
    }

//    @Override
//    public ProductEntity findById(UUID id) {
//        return productRepository.findById(id).orElseThrow();
//    }

    private void saveStore(ProductStoreSaveDto dto) {
        storeClient.post()
                .uri("/products")
                .body(dto)
                .retrieve()
                .body(ProductStoreSaveDto.class);
    }
    @Override
    public void purchase(UUID id) {
        var entity = productService.findById(id);
        var stock = entity.decreaseStock();
        productRepository.save(entity);
        if (stock.isNotAvailable())
            stockService.changeStatus(stock.getId(), stock.getStatus());
    }
    }

