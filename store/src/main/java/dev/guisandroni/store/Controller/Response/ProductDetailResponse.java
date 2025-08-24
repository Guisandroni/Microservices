package dev.guisandroni.store.Controller.Response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ProductDetailResponse(
        @JsonProperty("id")
        long id,
        @JsonProperty("name")
        String name
){
}
