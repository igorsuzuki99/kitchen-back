package com.carcara.oracle.kitchencloud.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_comanda")
public class Comanda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codComanda;
    private Timestamp horarioAbertura;
    private Timestamp horarioFechamento;

    @OneToOne
    @JoinColumn(name = "cod_venda")
    private VendaDiaria vendaDiaria;

    @OneToMany(mappedBy = "comanda")
    private List<ItemVendaDiaria> itemVendaDiarias;

    @OneToOne
    @JoinColumn(name = "cod_funcionario")
    private Funcionario funcionario;

}
