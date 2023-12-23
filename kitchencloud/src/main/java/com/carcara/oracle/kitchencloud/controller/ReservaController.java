package com.carcara.oracle.kitchencloud.controller;

import com.carcara.oracle.kitchencloud.service.ReservaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/reservas")
@CrossOrigin
@Tag(name = "RESERVA")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @GetMapping
    public ResponseEntity<Object> quantidadeReservas(@RequestParam LocalDateTime data1, @RequestParam LocalDateTime data2){
        return ResponseEntity.ok(reservaService.quantidadeReservas(data1, data2));
    }
}
