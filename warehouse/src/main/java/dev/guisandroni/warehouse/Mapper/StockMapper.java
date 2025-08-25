package dev.guisandroni.warehouse.Mapper;

import dev.guisandroni.warehouse.Controller.Request.StockSaveRequest;
import dev.guisandroni.warehouse.Controller.Response.StockSaveResponse;
import dev.guisandroni.warehouse.Entity.StockEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "SPRING")
public interface StockMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "product.id", source = "productId")
    @Mapping(target = "status",expression = "java(dev.guisandroni.warehouse.Entity.StockStatus.IN_CONFERENCE)")
    StockEntity toEntity(final StockSaveRequest request);

    @Mapping(target = "productId", source = "product.id")
    @Mapping(target = "productId", source = "product.name")

    StockSaveResponse toResponse(final StockEntity entity);
}