package com.carcara.oracle.kitchencloud.controller;

import com.carcara.oracle.kitchencloud.model.ViewVendas;
import com.carcara.oracle.kitchencloud.service.ViewVendasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/vendas")
@CrossOrigin
public class ViewVendasController {

    @Autowired
    private ViewVendasService viewVendasService;

    @GetMapping
    public ResponseEntity<List<ViewVendas>> listarVendas(){
        List<ViewVendas> vendas = viewVendasService.listarVendas();
        return ResponseEntity.ok().body(vendas);
    }
}
