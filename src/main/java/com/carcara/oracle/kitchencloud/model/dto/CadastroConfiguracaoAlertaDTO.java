package com.carcara.oracle.kitchencloud.model.dto;

import com.carcara.oracle.kitchencloud.model.enums.AcaoAlerta;
import com.carcara.oracle.kitchencloud.model.enums.CondicaoDisparoAlerta;
import com.carcara.oracle.kitchencloud.model.enums.Entidade;

public record CadastroConfiguracaoAlertaDTO(
        String nomeAlerta,
        String descricao,
        Entidade entidade,
        CondicaoDisparoAlerta condicaoDisparo,
        String valorParametro,
        AcaoAlerta acao,
        String destinatarios
) {
}
