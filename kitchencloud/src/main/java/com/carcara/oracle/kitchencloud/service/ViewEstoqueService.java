package com.carcara.oracle.kitchencloud.service;

import com.carcara.oracle.kitchencloud.model.ViewEstoque;
import com.carcara.oracle.kitchencloud.repository.ViewEstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViewEstoqueService {

    @Autowired
    private ViewEstoqueRepository viewEstoqueRepository;

    public List<ViewEstoque> listarViewEstoque(){
        return viewEstoqueRepository.findAll();
    }
}
