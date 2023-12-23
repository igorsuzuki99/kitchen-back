package com.carcara.oracle.kitchencloud.service;

import com.carcara.oracle.kitchencloud.model.ViewComanda;
import com.carcara.oracle.kitchencloud.repository.ViewComandaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViewComandaService {

    @Autowired
    private ViewComandaRepository viewComandaRepository;

    public List<ViewComanda> listarComandas(){
        return viewComandaRepository.findAll();
    }
}
