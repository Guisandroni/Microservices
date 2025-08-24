package dev.guisandroni.store.Controller.Response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ProductAvailableResponse (
        @JsonProperty("id")
        long id,
        @JsonProperty("name")
        String name
) {
}
