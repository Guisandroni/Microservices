package dev.guisandroni.warehouse.Controller.Request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ProductSaveRequest (
        @JsonProperty("name")
        String name
) {
}
