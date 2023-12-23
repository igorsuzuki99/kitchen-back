package com.carcara.oracle.kitchencloud.model.dto;

import java.util.List;

public record ExibicaoProdutosVendidos(

        List<ExibicaoRankPratoDTO> principal,
        List<ExibicaoRankPratoDTO> sobremesa,
        List<ExibicaoRankPratoDTO> bebida
        ) {
}
