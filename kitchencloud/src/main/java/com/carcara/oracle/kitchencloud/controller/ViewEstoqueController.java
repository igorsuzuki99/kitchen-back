package com.carcara.oracle.kitchencloud.controller;

import com.carcara.oracle.kitchencloud.model.ViewEstoque;
import com.carcara.oracle.kitchencloud.service.ViewEstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/view-estoque")
@CrossOrigin
public class ViewEstoqueController {

    @Autowired
    private ViewEstoqueService viewEstoqueService;
    
    @GetMapping
    public ResponseEntity<List<ViewEstoque>> listarViewEstoque(){
        return ResponseEntity.ok().body(viewEstoqueService.listarViewEstoque());
    }
}
