package com.carcara.oracle.kitchencloud.service;

import com.carcara.oracle.kitchencloud.model.Fornecedor;
import com.carcara.oracle.kitchencloud.model.Ingrediente;
import com.carcara.oracle.kitchencloud.model.dto.ExibicaoPainelFornecedorDTO;
import com.carcara.oracle.kitchencloud.repository.FornecedorRepository;
import com.carcara.oracle.kitchencloud.repository.IngredienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class IngredienteService {

    @Autowired
    private IngredienteRepository ingredienteRepository;

    @Autowired
    private FornecedorRepository fornecedorRepository;

    public Ingrediente definirEstoqueMinimo(Long codIngrediente, Float estoqueMinimo) {
        Optional<Ingrediente> ingrediente = ingredienteRepository.findById(codIngrediente);
        if(ingrediente.isEmpty()){
            // EXCESSÃO AQUI
        }
        ingrediente.get().setEstoqueMinimo(estoqueMinimo);
        ingredienteRepository.save(ingrediente.get());
        return ingrediente.get();
    }

    public List<Ingrediente> listarTodosIngredientes() {
        return ingredienteRepository.findAll();
    }

    public List<ExibicaoPainelFornecedorDTO> fornecedoresIngredientesEstoqueBaixo(Long idIngrediente){
        Optional<Ingrediente> ingrediente = ingredienteRepository.findById(idIngrediente);
        List<Fornecedor> fornecedores = correlacionarIngredientesComFornecedores(ingrediente.get());
        return fornecedores.stream().map(ExibicaoPainelFornecedorDTO::new).collect(Collectors.toList());
    }

    private List<Fornecedor> correlacionarIngredientesComFornecedores(Ingrediente ingrediente){
        List<Fornecedor> fornecedores = fornecedorRepository.findAll();
        return switch (ingrediente.getCategoriaIngrediente()) {
            case "Bebida" -> fornecedores.stream()
                    .filter(fornecedor -> fornecedor.getCategoria().equals("Bebida"))
                    .collect(Collectors.toList());
            case "Carnes" -> fornecedores.stream()
                    .filter(fornecedor -> fornecedor.getCategoria().equals("Frigorifico"))
                    .collect(Collectors.toList());
            case "Fruta" -> fornecedores.stream()
                    .filter(fornecedor -> fornecedor.getCategoria().equals("Horti Fruti"))
                    .collect(Collectors.toList());
            default -> fornecedores.stream()
                    .filter(fornecedor -> fornecedor.getCategoria().equals("Alimenticio"))
                    .collect(Collectors.toList());
        };
    }

    public List<Ingrediente> listarIngredienteEstoqueBaixo() {
        return ingredienteRepository.findIngredientesComQuantidadeAbaixoDoLimite();
    }
}
