package dev.guisandroni.warehouse.Controller;

import dev.guisandroni.warehouse.Controller.Request.StockSaveRequest;
import dev.guisandroni.warehouse.Controller.Response.StockSaveResponse;
import dev.guisandroni.warehouse.Mapper.StockMapper;
import dev.guisandroni.warehouse.Service.StockService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("stocks")
@AllArgsConstructor
public class StockController {

    private final StockService stockService;
    private final StockMapper mapper;

    @PostMapping
    @ResponseStatus(CREATED)
    StockSaveResponse save(@RequestBody final StockSaveRequest request) {
        var entity = mapper.toEntity(request);
        entity = stockService.save(entity);
        return mapper.toResponse(entity);
    }

    @PutMapping("{id}/released")
    @ResponseStatus(NO_CONTENT)
    void released(@PathVariable final UUID id) {
        stockService.released(id);
    }

    @DeleteMapping("{id}/inactived")
    @ResponseStatus(NO_CONTENT)
    void inactived(@PathVariable final UUID id) {
        stockService.inactive(id);
    }
}