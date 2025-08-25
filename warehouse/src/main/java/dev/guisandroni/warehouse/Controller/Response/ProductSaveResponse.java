package dev.guisandroni.warehouse.Controller.Response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public record ProductSaveResponse (
        @JsonProperty("id")
        UUID id,
        @JsonProperty("name")
        String name
) {
}
