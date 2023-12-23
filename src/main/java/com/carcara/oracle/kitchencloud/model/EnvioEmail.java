package com.carcara.oracle.kitchencloud.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
public class EnvioEmail {

    private String para;

    private String assunto;

    private String conteudo;


    public String getPara() {
        return para;
    }

    public void setPara(String para) {
        this.para = para;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public EnvioEmail(String para, String assunto, String conteudo) {
        this.para = para;
        this.assunto = assunto;
        this.conteudo = conteudo;
    }
}
