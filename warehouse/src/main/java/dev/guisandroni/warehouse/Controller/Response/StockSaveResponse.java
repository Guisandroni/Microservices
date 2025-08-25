package dev.guisandroni.warehouse.Controller.Response;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.guisandroni.warehouse.Entity.StockStatus;

import java.math.BigDecimal;
import java.util.UUID;

public record StockSaveResponse (
        @JsonProperty("id")
        UUID id,
        @JsonProperty("amount")
        Long amount,
        @JsonProperty("buyPrice")
        BigDecimal buyPrice,
        @JsonProperty("status")
        StockStatus status,
        @JsonProperty("sellPrice")
        BigDecimal sellPrice,
        @JsonProperty("productId")
        UUID productId,
        @JsonProperty("productName")
        String productName
){
}
