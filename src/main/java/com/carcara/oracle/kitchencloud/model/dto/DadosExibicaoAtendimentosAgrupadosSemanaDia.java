package com.carcara.oracle.kitchencloud.model.dto;

import com.carcara.oracle.kitchencloud.model.VendaPrato;
import java.util.List;

import java.util.Map;

public record DadosExibicaoAtendimentosAgrupadosSemanaDia(

        Map<String  , Long> semana,
        Map<java.time.LocalTime, Long> dia,
        Map<String, String> funcionarios,
        List<VendaPrato> pratosMaisVendidos
) {
}
