package com.carcara.oracle.kitchencloud.controller;

import com.carcara.oracle.kitchencloud.model.Ingrediente;
import com.carcara.oracle.kitchencloud.model.dto.ExibicaoPainelFornecedorDTO;
import com.carcara.oracle.kitchencloud.service.IngredienteService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ingrediente")
@CrossOrigin
@Tag(name = "INGREDIENTE")
public class IngredienteController {

    @Autowired
    private IngredienteService ingredienteService;

    @PostMapping("estoque-minimo")
    public Ingrediente definirEstoqueMinimo(Long codIngrediente, Float estoqueMinimo){
        return ingredienteService.definirEstoqueMinimo(codIngrediente,estoqueMinimo);
    }

    @GetMapping("listar")
    public List<Ingrediente> listarTodosIngredientes (){
        return ingredienteService.listarTodosIngredientes();
    }

    @GetMapping("/fornecedores-ingredientes")
    public List<ExibicaoPainelFornecedorDTO> forncedores(@RequestParam Long idIngrediente){
        return ingredienteService.fornecedoresIngredientesEstoqueBaixo(idIngrediente);
    }

    @GetMapping("/estoque-baixo")
    public List<Ingrediente> listarIngredienteEstoqueBaixo(){
        return ingredienteService.listarIngredienteEstoqueBaixo();
    }
}
