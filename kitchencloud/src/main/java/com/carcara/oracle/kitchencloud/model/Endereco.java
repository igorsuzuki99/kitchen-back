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
@Table(name = "tb_endereco")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long codEndereco;

    private String numeroCasa;
    private String cep;

    @ManyToOne
    @JoinColumn(name = "cod_cidade")
    private Cidade cidade;

    @OneToMany(mappedBy = "endereco")
    private List<Fornecedor> fornecedores;


}
