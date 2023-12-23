package com.carcara.oracle.kitchencloud.controller;

import com.carcara.oracle.kitchencloud.model.dto.ExibicaoProdutosVendidos;
import com.carcara.oracle.kitchencloud.service.PratoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pratos")
@CrossOrigin
@Tag(name = "PRATO")
public class PratoController {

    @Autowired
    private PratoService pratoService;

    @GetMapping("/mais-vendidos")
    public ExibicaoProdutosVendidos maisVendidos(){
        return pratoService.maisVendidos();
    }

    @GetMapping("/menos-vendidos")
    public ExibicaoProdutosVendidos menosVendidos(){
        return pratoService.menosVendidos();
    }
}
