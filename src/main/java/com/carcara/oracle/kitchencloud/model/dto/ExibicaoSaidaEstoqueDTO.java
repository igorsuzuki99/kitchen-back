package com.carcara.oracle.kitchencloud.model.dto;

import com.carcara.oracle.kitchencloud.model.SaidaEstoque;

import java.time.LocalDate;

public record ExibicaoSaidaEstoqueDTO(
        Long codSaida,
        Integer quantidadeSaida,
        LocalDate dataSaida,
        String motivoSaida,
        Long codEstoque,
        Long codPrato
) {
    public ExibicaoSaidaEstoqueDTO(SaidaEstoque saidaEstoque) {
        this(saidaEstoque.getCodSaida(),
                saidaEstoque.getQuantidadeSaida(),
                saidaEstoque.getDataSaida(),
                saidaEstoque.getMotivoSaida(),
                saidaEstoque.getEstoque().getCodEstoque(),
                saidaEstoque.getCardapio().getCodPrato());
    }
}
