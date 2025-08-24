package dev.guisandroni.store.Controller;


import dev.guisandroni.store.Controller.Request.ProductSaveRequest;
import dev.guisandroni.store.Controller.Response.ProductAvailableResponse;
import dev.guisandroni.store.Controller.Response.ProductDetailResponse;
import dev.guisandroni.store.Controller.Response.ProductSaveResponse;
import dev.guisandroni.store.Mapper.ProductMapper;
import dev.guisandroni.store.Service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("products")
@AllArgsConstructor
public class ProductController {

    private final ProductService service;
    private final ProductMapper mapper;

    @PostMapping
    @ResponseStatus(CREATED)
    ProductSaveResponse create (@RequestBody final ProductSaveRequest request){
        var entity = mapper.toEntity(request);
        entity = service.save(entity);
        return mapper.toResponse(entity);
    }

    @PostMapping("{id}/purchase")
    @ResponseStatus(NO_CONTENT)
    void purchase(@PathVariable final long id){
        service.purchase(id);
    }

    @GetMapping
    List<ProductAvailableResponse> listAvailable(){
        var entities = service.findAllActive();
        return mapper.toResponse(entities);
    }

    @GetMapping("{id}")
    ProductDetailResponse findByID(@PathVariable final long id){
        var dto = service.findInfo(id);
        return mapper.toResponse(dto);
    }
}
