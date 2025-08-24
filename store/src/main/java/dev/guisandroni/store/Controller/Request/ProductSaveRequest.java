package dev.guisandroni.store.Controller.Request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ProductSaveRequest(
        @JsonProperty("id")
        long id,
        @JsonProperty("name")
        String name
) {

}
