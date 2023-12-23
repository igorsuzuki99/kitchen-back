package com.carcara.oracle.kitchencloud.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "v_estoque")
@Builder
public class ViewEstoque {

    @Id
    private Long codIngrediente;
    private String nomeIngrediente;
    private String unidadeMedida;
    private String categoriaIngrediente;
    private Integer quantidade;
    private Double capacidade;
    private Double estoqueMinimo;
}
