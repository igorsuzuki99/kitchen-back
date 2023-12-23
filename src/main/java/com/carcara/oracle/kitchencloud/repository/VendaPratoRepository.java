package com.carcara.oracle.kitchencloud.repository;

import com.carcara.oracle.kitchencloud.model.VendaPrato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


import java.time.LocalDateTime;

public interface VendaPratoRepository extends JpaRepository<VendaPrato, Long> {

    @Query(value = "SELECT ivd.cod_prato, c.nome_prato, COUNT(ivd.cod_prato) AS quantidade_vendida\n" +
            "FROM tb_item_venda_diaria ivd\n" +
            "INNER JOIN tb_venda_diaria vd ON ivd.cod_venda = vd.cod_venda\n" +
            "INNER JOIN tb_cardapio c ON ivd.cod_prato = c.cod_prato\n" +
            "WHERE TRUNC(vd.data_venda) = TO_DATE(:data, 'YYYY-MM-DD')\n" +
            "GROUP BY ivd.cod_prato, c.nome_prato\n" +
            "ORDER BY COUNT(ivd.cod_prato) DESC FETCH FIRST 5 ROWS ONLY",nativeQuery = true)
    List<VendaPrato> findByDate(@Param("data") String data);
}
