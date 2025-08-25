package dev.guisandroni.warehouse.Service.Impl;

import dev.guisandroni.warehouse.Dto.StockStatusMessage;
import dev.guisandroni.warehouse.Entity.StockEntity;
import dev.guisandroni.warehouse.Entity.StockStatus;
import dev.guisandroni.warehouse.Repository.StockRepository;
import dev.guisandroni.warehouse.Service.ProductChangeAvailability;
import dev.guisandroni.warehouse.Service.ProductQueryService;
import dev.guisandroni.warehouse.Service.StockService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class StockServiceImpl implements StockService {

    private final StockRepository repository;
    private final ProductQueryService productService;
    private final ProductChangeAvailability producer;


    @Override
    public StockEntity save(final StockEntity stock) {
        stock.setProduct(productService.findById(stock.getProduct().getId()));
        return repository.save(stock);
    }

    @Override
    public void released(UUID id) {
        changeStatus(id, StockStatus.AVAILABLE);
    }



    @Override
    public void inactive(UUID id) {
        changeStatus(id, StockStatus.UNAVAILABLE);
    }

    @Override
    public void changeStatus(UUID id, StockStatus status) {
        var entity = repository.findById(id).orElseThrow();
        entity.setStatus(status);
        repository.save(entity);
        producer.notifyStatusChange(
                new StockStatusMessage(
                        entity.getProduct()
                                .getId(), status));
    }
    }

