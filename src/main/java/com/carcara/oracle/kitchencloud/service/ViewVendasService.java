package com.carcara.oracle.kitchencloud.service;

import com.carcara.oracle.kitchencloud.model.ViewVendas;
import com.carcara.oracle.kitchencloud.repository.ViewVendasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViewVendasService {

    @Autowired
    private ViewVendasRepository viewVendasRepository;

    public List<ViewVendas> listarVendas(){
        return viewVendasRepository.findAll();
    }
}
