package com.carcara.oracle.kitchencloud.repository;

import com.carcara.oracle.kitchencloud.model.RankVendaProduto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RankVendaProdutoRepository extends JpaRepository<RankVendaProduto, Long> {

    @Query(value = "WITH ProdutosMaisVendidos AS (SELECT COD_PRATO, NOME_PRATO, TO_CHAR(DATA_VENDA, 'D') AS DIA_DA_SEMANA_NUM, TO_CHAR(DATA_VENDA, 'Day') AS DIA_DA_SEMANA, SUM(QUANTIDADE) AS QUANTIDADE_TOTAL, SUM(VALOR_ITEM * QUANTIDADE) AS VALOR_TOTAL_PRODUTO FROM V_VENDAS WHERE TO_NUMBER(TO_CHAR(DATA_VENDA, 'D')) = ?1 AND DATA_VENDA BETWEEN TO_DATE(?2, 'YYYY-MM-DD') AND TO_DATE(?3, 'YYYY-MM-DD') GROUP BY COD_PRATO, NOME_PRATO, TO_CHAR(DATA_VENDA, 'D'), TO_CHAR(DATA_VENDA, 'Day') ORDER BY QUANTIDADE_TOTAL DESC) SELECT PMV.COD_PRATO, PMV.NOME_PRATO, PMV.DIA_DA_SEMANA_NUM, PMV.DIA_DA_SEMANA, PMV.QUANTIDADE_TOTAL, PMV.VALOR_TOTAL_PRODUTO, (PMV.VALOR_TOTAL_PRODUTO / (SELECT SUM(VALOR_TOTAL_PRODUTO) FROM ProdutosMaisVendidos)) * 100 AS IMPACTO_PORCENTAGEM FROM ProdutosMaisVendidos PMV ORDER BY IMPACTO_PORCENTAGEM DESC", nativeQuery = true)
    List<RankVendaProduto> rankDeVendas(Integer diaSemana, String data1, String data2);
}
