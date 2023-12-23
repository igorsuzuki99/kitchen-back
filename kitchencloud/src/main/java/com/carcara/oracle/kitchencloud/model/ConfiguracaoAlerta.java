package com.carcara.oracle.kitchencloud.model;

import com.carcara.oracle.kitchencloud.model.dto.CadastroConfiguracaoAlertaDTO;
import com.carcara.oracle.kitchencloud.model.enums.AcaoAlerta;
import com.carcara.oracle.kitchencloud.model.enums.CondicaoDisparoAlerta;
import com.carcara.oracle.kitchencloud.model.enums.Entidade;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConfiguracaoAlerta {

    @Id
    @Column(name = "ID")
    private Integer id;

    @Column(name = "nome_alerta")
    private String nomeAlerta;

    private String descricao;
    @Enumerated(EnumType.STRING)
    private Entidade entidade;
    
    @Column(name = "condicao_disparo")
    @Enumerated(EnumType.STRING)
    private CondicaoDisparoAlerta condicaoDisparo;
    
    @Column(name = "valor_parametro")
    private String valorParametro;

    @Enumerated(EnumType.STRING)
    private AcaoAlerta acao;
    private String destinatarios;
    
    @Column(name = "ativo")
    private Integer ativo;

    public ConfiguracaoAlerta(CadastroConfiguracaoAlertaDTO cadastroConfiguracaoAlertaDTO) {
        this.nomeAlerta = cadastroConfiguracaoAlertaDTO.nomeAlerta();
        this.descricao = cadastroConfiguracaoAlertaDTO.descricao();
        this.entidade = cadastroConfiguracaoAlertaDTO.entidade();
        this.condicaoDisparo = cadastroConfiguracaoAlertaDTO.condicaoDisparo();
        this.valorParametro = cadastroConfiguracaoAlertaDTO.valorParametro();
        this.acao = cadastroConfiguracaoAlertaDTO.acao();
        this.destinatarios = cadastroConfiguracaoAlertaDTO.destinatarios();
        this.ativo = 1;
    }

    // Getters and setters (ou Lombok @Data) e outros métodos, construtores, etc.

}