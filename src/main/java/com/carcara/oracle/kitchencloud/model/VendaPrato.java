package com.carcara.oracle.kitchencloud.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Subselect;

@Subselect(value = "SELECT ivd.cod_prato, c.nome_prato, COUNT(ivd.cod_prato) AS quantidade_vendida\n" +
        "FROM tb_item_venda_diaria ivd\n" +
        "INNER JOIN tb_venda_diaria vd ON ivd.cod_venda = vd.cod_venda\n" +
        "INNER JOIN tb_cardapio c ON ivd.cod_prato = c.cod_prato\n" +
        "WHERE TRUNC(vd.data_venda) = TO_DATE('2023-09-22', 'YYYY-MM-DD')\n" +
        "GROUP BY ivd.cod_prato, c.nome_prato\n" +
        "ORDER BY COUNT(ivd.cod_prato) DESC FETCH FIRST 6 ROWS ONLY")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VendaPrato {
    @Id
    private Long codPrato;

    private String nomePrato;

    private Long quantidadeVendida;
}
