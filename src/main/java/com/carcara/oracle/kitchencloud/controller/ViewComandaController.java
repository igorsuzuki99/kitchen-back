package com.carcara.oracle.kitchencloud.controller;

import com.carcara.oracle.kitchencloud.model.ViewComanda;
import com.carcara.oracle.kitchencloud.service.ViewComandaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/view-comanda")
@CrossOrigin
public class ViewComandaController {

    @Autowired
    private ViewComandaService viewComandaService;

    @GetMapping
    public ResponseEntity<List<ViewComanda>> listarComandas(){
        return ResponseEntity.ok().body(viewComandaService.listarComandas());
    }
}
