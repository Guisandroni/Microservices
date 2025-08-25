package dev.guisandroni.warehouse.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public record ProductStoreSaveDto (
        @JsonProperty("id")
        UUID id,
        @JsonProperty("name")
        String name,
        @JsonProperty("active")
        Boolean active
){
}
