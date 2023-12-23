package com.carcara.oracle.kitchencloud.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_venda_diaria")
public class VendaDiaria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codVenda;
    private LocalDateTime dataVenda;
    private BigDecimal totalPagamento;

}
