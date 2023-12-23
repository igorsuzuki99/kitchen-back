package com.carcara.oracle.kitchencloud.model.dto;

import com.carcara.oracle.kitchencloud.model.Fornecedor;

public record ExibicaoPainelFornecedorDTO(
        String localizacao,
        String razaoSocial,
        String cnpj,
        String telefone,
        String prazoEntrega,
        String categoria,
        Integer custoFrete) {

    public ExibicaoPainelFornecedorDTO(Fornecedor fornecedor){
        this(fornecedor.getEndereco().getCep() + ", " +
                        fornecedor.getEndereco().getCidade().getNomeCidade(),
                fornecedor.getRazaoSocial(),
                fornecedor.getCnpjFornecedor(),
                fornecedor.getTelefone(),
                fornecedor.getPrazoEntrega(),
                fornecedor.getCategoria(),
                fornecedor.getCustoFrete());
    }
}
