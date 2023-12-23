package com.carcara.oracle.kitchencloud.service;


import com.carcara.oracle.kitchencloud.model.RankVendaProduto;
import com.carcara.oracle.kitchencloud.repository.RankVendaProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RankVendaProdutoService {

    @Autowired
    private RankVendaProdutoRepository rankVendaProdutoRepository;

    public List<RankVendaProduto> rankVendaProdutos (Integer diaSemana,String data1, String data2){
        return rankVendaProdutoRepository.rankDeVendas(diaSemana,data1, data2);
    }
}
