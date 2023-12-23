package com.carcara.oracle.kitchencloud.model;

import com.carcara.oracle.kitchencloud.model.dto.CadastroCompraDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_compra")
public class Compra {

    @Id
    private Long codCompra;

    private LocalDate dataCompra;
    private char statusCompra;
    private Integer valorCompra;

    @ManyToOne
    @JoinColumn(name = "cod_fornecedor")
    private Fornecedor fornecedor;

    @OneToMany(mappedBy = "compra")
    private List<ItemCompra> itensCompra;

    public Compra(CadastroCompraDTO cadastroCompraDTO, Fornecedor fornecedor) {
        this.dataCompra = LocalDate.now();
        this.valorCompra = cadastroCompraDTO.valorCompra();
        this.fornecedor = fornecedor;
        this.statusCompra = 'A';
        this.dataCompra = LocalDate.now();
    }
}
