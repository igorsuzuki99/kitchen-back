package com.carcara.oracle.kitchencloud.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "v_vendas")
public class ViewVendas {

    @Id
    @JsonProperty("codItemVenda")

    private Long codItemVenda;
    @JsonProperty("codVenda")

    private Long codVenda;
    private Long codPrato;
    private Long quantidade;
    private BigDecimal valorItem;
    private LocalDateTime dataVenda;
    private String nomePrato;
    private Integer valorTotal;

}
