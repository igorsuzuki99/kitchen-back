package com.carcara.oracle.kitchencloud.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_folga")
public class Folga {

    @Id
    private Long codFolga;

    private String dataFolga;
    private String motivoFolga;

    @ManyToMany
    @JoinTable(
            name = "tb_folga_funcionario",
            joinColumns = @JoinColumn(name = "cod_folga"),
            inverseJoinColumns = @JoinColumn(name = "cod_funcionario")
    )
    private List<Funcionario> funcionarios;
}
