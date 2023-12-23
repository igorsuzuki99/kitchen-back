package com.carcara.oracle.kitchencloud.model;

import com.carcara.oracle.kitchencloud.model.dto.SaidaEstoqueDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_saida_estoque")
public class SaidaEstoque {

    @Id
    private Long codSaida;
    private Integer quantidadeSaida;
    private LocalDate dataSaida;
    private String motivoSaida;

    @ManyToOne
    @JoinColumn(name = " cod_estoque")
    private Estoque estoque;

    @ManyToOne
    @JoinColumn(name = " cod_prato")
    private Cardapio cardapio;

    public SaidaEstoque(SaidaEstoqueDTO saidaEstoqueDTO, Estoque estoque, Cardapio cardapio) {
        this.quantidadeSaida = saidaEstoqueDTO.quantidadeSaida();
        this.dataSaida = LocalDate.now();
        this.motivoSaida = saidaEstoqueDTO.motivoSaida();
        this.estoque = estoque;
        this.cardapio = cardapio;
    }
}
