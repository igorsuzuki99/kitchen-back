package com.carcara.oracle.kitchencloud.model.dto;

public record VendaDiaSemanaDTO(
        Long domingo,
        Long segundaFeira,
        Long tercaFeira,
        Long quartaFeira,
        Long quintaFeira,
        Long sextaFeira,
        Long sabado
) {
}
