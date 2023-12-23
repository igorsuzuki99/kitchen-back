package com.carcara.oracle.kitchencloud.model.dto;

import com.carcara.oracle.kitchencloud.model.Funcionario;

import java.util.Collections;

public record ExibicaoFuncionarioDTO(Long id, String nome, String funcao, String diaFolga) {

    public ExibicaoFuncionarioDTO(Funcionario funcionario){
        this(funcionario.getCodFuncionario(),
                funcionario.getNomeFuncionario(),
                funcionario.getCargo(),
                funcionario.getFolgas().get(0).getDataFolga());
    }
}
