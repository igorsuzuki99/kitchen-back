package com.carcara.oracle.kitchencloud.controller;

import com.carcara.oracle.kitchencloud.model.dto.CadastroCompraDTO;
import com.carcara.oracle.kitchencloud.service.CompraService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("compra")
@CrossOrigin
@Tag(name = "COMPRAS")
public class CompraController {

    @Autowired
    private CompraService compraService;

    @PostMapping
    public void cadastrarCompra(@RequestBody CadastroCompraDTO cadastroCompraDTO){
        compraService.cadastrarCompra(cadastroCompraDTO);
    }

}
