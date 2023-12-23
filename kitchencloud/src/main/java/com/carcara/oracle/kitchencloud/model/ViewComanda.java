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
@Table(name = "view_dados_venda")
public class ViewComanda {

    @Id
    @JsonProperty("nf")
    private Long idComanda;

    @JsonProperty("qtdItens")
    private Integer quantidadeItemVenda;

    @JsonProperty("preco")
    private BigDecimal totalPagamentoVenda;

    @JsonProperty("abertura")
    private LocalDateTime horarioAberturaComanda;

    @JsonProperty("fechamento")
    private LocalDateTime horarioFechamentoComanda;

    @JsonProperty("atendente")
    private String nomeFuncionario;
}
