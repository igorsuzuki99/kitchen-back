package com.carcara.oracle.kitchencloud.model;

import com.carcara.oracle.kitchencloud.model.dto.CadastroItemCompraDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Optional;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_item_compra")
public class ItemCompra {

    @Id
    private Long codItemCompra;

    private char statusItem;

    private String descricaoItem;

    @ManyToOne
    @JoinColumn(name = "cod_compra")
    private Compra compra;

    @OneToMany(mappedBy = "itemCompra")
    private List<Estoque> estoques;

    @ManyToOne
    @JoinColumn(name = "cod_ingrediente")
    private Ingrediente ingrediente;


    public ItemCompra(CadastroItemCompraDTO itens, Ingrediente ingrediente, Fornecedor fornecedor, Compra compra) {
        this.descricaoItem = itens.descricaoItem();
        this.compra = compra;
        this.ingrediente = ingrediente;
        this.statusItem = 'N';
    }
}
