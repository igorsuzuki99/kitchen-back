package com.carcara.oracle.kitchencloud.model.dto;

import java.time.LocalDate;
import java.util.List;


public record CadastroCompraDTO(
        Integer valorCompra,
        Long idFornecedor,
        List<CadastroItemCompraDTO> itens
        ) {
}
