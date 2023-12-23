package com.carcara.oracle.kitchencloud.controller;

import com.carcara.oracle.kitchencloud.model.dto.CalculoDTO;
import com.carcara.oracle.kitchencloud.service.CalculoComandasService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/painel-receitas")
@CrossOrigin
@Tag(name = "RECEITAS")
public class CalculoComandaController {

    @Autowired
    private CalculoComandasService calculoComandasService;



    @GetMapping
    public ResponseEntity<CalculoDTO> painelReceitas(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFim){

        CalculoDTO calculoPainelReceita = calculoComandasService.calculoPainelReceitas(dataInicio, dataFim);
        return ResponseEntity.ok().body(calculoPainelReceita);
    }
}
