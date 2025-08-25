package dev.guisandroni.warehouse.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.guisandroni.warehouse.Entity.StockStatus;

import java.util.UUID;

public record StockStatusMessage(
        @JsonProperty("id")
        UUID id,
        @JsonProperty("status")
        StockStatus status)
        { }

