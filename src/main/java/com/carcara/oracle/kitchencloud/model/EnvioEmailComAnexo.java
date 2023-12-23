package com.carcara.oracle.kitchencloud.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.core.io.FileSystemResource;

@Getter
@Setter
public class EnvioEmailComAnexo {

    private EnvioEmail envioEmail;
    private FileSystemResource pdfBase64;

    public EnvioEmailComAnexo(EnvioEmail envioEmail, FileSystemResource pdfBase64) {
        this.envioEmail = envioEmail;
        this.pdfBase64 = pdfBase64;
    }

    // getters e setters, se necessário
}
