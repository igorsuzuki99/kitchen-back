package com.carcara.oracle.kitchencloud.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_fornecedor")
public class Fornecedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codFornecedor;
    private String nomeFornecedor;
    private String cnpjFornecedor;
    private String telefone;
    private String prazoEntrega;
    private String categoria;
    private String razaoSocial;
    private Integer custoFrete;

    @ManyToOne
    @JoinColumn(name = "cod_endereco")
    private Endereco endereco;

    @OneToMany(mappedBy = "fornecedor")
    private List<Compra> compras;
}
