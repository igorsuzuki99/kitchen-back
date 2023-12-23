package com.carcara.oracle.kitchencloud.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.Subselect;
import org.springframework.format.annotation.NumberFormat;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Subselect("")
public class RankVendaProduto {

    @Id
    private Long codPrato;

    private String nomePrato;

    private String diaDaSemana;

    private Integer quantidadeTotal;

    private BigDecimal valorTotalProduto;

    private BigDecimal impactoPorcentagem;

    public String getDiaDaSemana() {
        return diaDaSemana.replaceAll(" ","");
    }

    public BigDecimal getImpactoPorcentagem() {
        return impactoPorcentagem.setScale(2, BigDecimal.ROUND_HALF_UP);
    }
}
