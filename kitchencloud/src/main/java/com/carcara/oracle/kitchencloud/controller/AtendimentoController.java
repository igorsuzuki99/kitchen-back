package com.carcara.oracle.kitchencloud.controller;

import com.carcara.oracle.kitchencloud.model.Comanda;
import com.carcara.oracle.kitchencloud.model.VendaPrato;
import com.carcara.oracle.kitchencloud.model.dto.DadosExibicaoAtendimentosAgrupadosSemanaDia;
import com.carcara.oracle.kitchencloud.service.AtendimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;


@RestController("atendimento")
@CrossOrigin
public class AtendimentoController {
    @Autowired
    private AtendimentoService atendimentoService;
    @GetMapping("diario/{data}")
    public DadosExibicaoAtendimentosAgrupadosSemanaDia atendimento(@PathVariable LocalDateTime data){
        return atendimentoService.atendimentoDuranteDia(data);
    }

    @GetMapping("pratos-mais-vendidos")
    public List<VendaPrato> pratosMaisVendidos(LocalDateTime data){
        return atendimentoService.pratosMaisVendidos(data);
    }


}
