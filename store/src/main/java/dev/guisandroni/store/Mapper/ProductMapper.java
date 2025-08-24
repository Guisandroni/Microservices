package dev.guisandroni.store.Mapper;

import dev.guisandroni.store.Controller.Request.ProductSaveRequest;
import dev.guisandroni.store.Controller.Response.ProductAvailableResponse;
import dev.guisandroni.store.Controller.Response.ProductDetailResponse;
import dev.guisandroni.store.Controller.Response.ProductSaveResponse;
import dev.guisandroni.store.Dto.ProductInfoDto;
import dev.guisandroni.store.Entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.math.BigDecimal;
import java.util.List;

@Mapper()
public interface ProductMapper {

    ProductInfoDto toDTO (final ProductEntity entity, final BigDecimal price);
    @Mapping(target = "active", constant = "false")
    ProductEntity toEntity(final ProductSaveRequest request);
    ProductSaveResponse toResponse (final ProductEntity entity);
    List<ProductAvailableResponse> toResponse(final List<ProductEntity> entities);
    ProductDetailResponse toResonse(final ProductInfoDto dto);
}
