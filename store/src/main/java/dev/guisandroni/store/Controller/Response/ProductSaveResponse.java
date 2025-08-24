package dev.guisandroni.store.Controller.Response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ProductSaveResponse(
        @JsonProperty("id")
        long id,
        @JsonProperty("name")
        String name,
         @JsonProperty("active")
        Boolean active
) {
}
