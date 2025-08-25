package dev.guisandroni.warehouse.Controller;


import dev.guisandroni.warehouse.Controller.Request.ProductSaveRequest;
import dev.guisandroni.warehouse.Controller.Response.ProductDetailResponse;
import dev.guisandroni.warehouse.Controller.Response.ProductSaveResponse;
import dev.guisandroni.warehouse.Mapper.ProductMapper;
import dev.guisandroni.warehouse.Service.ProductQueryService;
import dev.guisandroni.warehouse.Service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("products")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductQueryService productQueryService;
    private final ProductMapper mapper;

    @PostMapping
    @ResponseStatus(CREATED)
    ProductSaveResponse create(@RequestBody final ProductSaveRequest request) {
        var entity = mapper.toEntity(request);
        entity = productService.save(entity);
        return mapper.toResponse(entity);
    }

    @PutMapping("{id}/purchased")
    @ResponseStatus(NO_CONTENT)
    void purchased(@PathVariable final UUID id) {
        productService.purchase(productQueryService.findById(id).getId());
    }

    @GetMapping("{id}")
    ProductDetailResponse findById(@PathVariable final UUID id) {
        var dto = productQueryService.findById(id);
        return mapper.toDetailResponse(dto);
    }
}