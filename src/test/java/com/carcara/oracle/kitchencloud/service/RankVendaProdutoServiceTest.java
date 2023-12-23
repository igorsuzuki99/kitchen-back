package com.carcara.oracle.kitchencloud.service;

import com.carcara.oracle.kitchencloud.model.RankVendaProduto;
import com.carcara.oracle.kitchencloud.repository.RankVendaProdutoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@SpringBootTest
public class RankVendaProdutoServiceTest {

    @Mock
    private RankVendaProdutoRepository rankVendaProdutoRepository;

    @InjectMocks
    private RankVendaProdutoService rankVendaProdutoService;

    RankVendaProduto rankVendaProduto;

    @BeforeEach
    public void setUp (){
        rankVendaProduto = RankVendaProduto.builder().codPrato(1L)
                .nomePrato("Prato1").diaDaSemana("Domingo").quantidadeTotal(10)
                .valorTotalProduto(new BigDecimal("10"))
                .impactoPorcentagem(new BigDecimal("10")).build();
    }

    @Test
    public void rankVendaProdutosOk(){
        Mockito.when(rankVendaProdutoRepository
                .rankDeVendas(1,"2023-08-01","2023-08-02"))
                .thenReturn(Collections.singletonList(rankVendaProduto));

        List<RankVendaProduto> rankVendaProdutos = rankVendaProdutoService.rankVendaProdutos(1,"2023-08-01","2023-08-02");

        Assertions.assertEquals(Collections.singletonList(rankVendaProduto),rankVendaProdutos);
        Mockito.verify(rankVendaProdutoRepository,Mockito.times(1)).rankDeVendas(1,"2023-08-01","2023-08-02");
    }
}
