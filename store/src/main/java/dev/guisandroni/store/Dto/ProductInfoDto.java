package dev.guisandroni.store.Dto;

import java.math.BigDecimal;

public record ProductInfoDto(

        long id,
        String name,
        BigDecimal price
)
{

}
