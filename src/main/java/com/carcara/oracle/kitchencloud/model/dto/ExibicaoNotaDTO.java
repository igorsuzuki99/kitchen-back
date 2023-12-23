package com.carcara.oracle.kitchencloud.model.dto;

import com.carcara.oracle.kitchencloud.model.Nota;

import java.time.LocalDateTime;

public record ExibicaoNotaDTO(Long codNota, Integer notaAtendimento, String comentario, LocalDateTime dataAvaliacao) {

    public ExibicaoNotaDTO(Nota nota){
        this(nota.getCodNota(),
                nota.getNotaAtendimento(),
                nota.getComentario(),
                nota.getDataAvaliacao());
    }
}
