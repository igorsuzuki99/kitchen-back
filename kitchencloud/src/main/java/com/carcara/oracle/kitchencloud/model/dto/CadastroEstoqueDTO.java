package com.carcara.oracle.kitchencloud.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

public record CadastroEstoqueDTO(
        Integer pesoProduto,
        Integer quantidadeProduto,
        LocalDate dataValidade,
        Long codItemCompra
) {
}
