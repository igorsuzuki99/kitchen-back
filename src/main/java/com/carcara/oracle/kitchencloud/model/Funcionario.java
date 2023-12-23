package com.carcara.oracle.kitchencloud.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_funcionario")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codFuncionario;
    private String nomeFuncionario;
    private String cpfFuncionario;
    private Date dataTrabalho;
    private Double horasTrabalhadas;
    private String cargo;

    @ManyToMany(mappedBy = "funcionarios")
    private List<Folga> folgas;

}
