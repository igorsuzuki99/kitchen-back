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
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_cidade")
public class Cidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codCidade;
    private String nomeCidade;

    @ManyToOne
    @JoinColumn(name = "cod_estado")
    private Estado estado;

    @OneToMany(mappedBy = "cidade")
    private List<Endereco> enderecos;

}
