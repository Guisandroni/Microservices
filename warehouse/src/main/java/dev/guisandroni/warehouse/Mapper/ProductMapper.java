package dev.guisandroni.warehouse.Mapper;


import dev.guisandroni.warehouse.Controller.Request.ProductSaveRequest;
import dev.guisandroni.warehouse.Controller.Response.ProductDetailResponse;
import dev.guisandroni.warehouse.Controller.Response.ProductSaveResponse;
import dev.guisandroni.warehouse.Dto.ProductStoreSaveDto;
import dev.guisandroni.warehouse.Entity.ProductEntity;
import dev.guisandroni.warehouse.Entity.StockEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface  ProductMapper {
  //  @Mapping(target = "id", ignore = true)
    //@Mapping(target = "stocks" , ignore = true)
  ProductEntity toEntity(final ProductSaveRequest request);

    ProductSaveResponse toResponse(final ProductEntity entity);

     ProductStoreSaveDto toDTO(final ProductEntity entity);
   ProductDetailResponse toDetailResponse(final ProductEntity entity);

}
