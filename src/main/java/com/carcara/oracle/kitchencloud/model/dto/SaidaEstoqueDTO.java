package com.carcara.oracle.kitchencloud.model.dto;

import java.time.LocalDate;

public record SaidaEstoqueDTO(
        Integer quantidadeSaida,
        String motivoSaida,
        Long codEstoque,
        Long codPrato
) {
}
