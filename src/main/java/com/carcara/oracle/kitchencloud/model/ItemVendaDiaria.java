package com.carcara.oracle.kitchencloud.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_item_venda_diaria")
public class ItemVendaDiaria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codItemVenda;
    private Integer quantidade;

    @ManyToOne
    @JoinColumn(name = "cod_comanda")
    private Comanda comanda;

    @ManyToOne
    @JoinColumn(name = "cod_venda")
    private VendaDiaria vendaDiaria;

    @ManyToOne
    @JoinColumn(name = "cod_prato")
    private Cardapio cardapio;

}
