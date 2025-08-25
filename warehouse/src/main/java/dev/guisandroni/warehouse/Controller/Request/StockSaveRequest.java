package dev.guisandroni.warehouse.Controller.Request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.UUID;

public record StockSaveRequest (
        @JsonProperty("amount")
        Long amount,
        @JsonProperty("buyPrice")
        BigDecimal buyPrice,
        @JsonProperty("sellPrice")
        BigDecimal sellPrice,
        @JsonProperty("productId")
        UUID productId
){
}
