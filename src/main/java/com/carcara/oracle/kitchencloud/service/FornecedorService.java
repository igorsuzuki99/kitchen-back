package com.carcara.oracle.kitchencloud.service;

import com.carcara.oracle.kitchencloud.model.Fornecedor;
import com.carcara.oracle.kitchencloud.model.dto.ExibicaoPainelFornecedorDTO;
import com.carcara.oracle.kitchencloud.repository.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FornecedorService {

    @Autowired
    private FornecedorRepository fornecedorRepository;

    public ExibicaoPainelFornecedorDTO exibirPainelFornecedor(Long idFornecedor){
        Optional<Fornecedor> fornecedor = fornecedorRepository.findById(idFornecedor);
        return new ExibicaoPainelFornecedorDTO(fornecedor.get());
    }

    public List<ExibicaoPainelFornecedorDTO> exibirFornecedores(){
        List<Fornecedor> fornecedores = fornecedorRepository.findAll();
        return fornecedores.stream().map(fornecedor -> new ExibicaoPainelFornecedorDTO(fornecedor)).toList();
    }
}
