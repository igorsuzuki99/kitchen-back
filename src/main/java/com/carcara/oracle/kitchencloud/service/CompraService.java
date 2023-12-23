package com.carcara.oracle.kitchencloud.service;

import com.carcara.oracle.kitchencloud.model.Compra;
import com.carcara.oracle.kitchencloud.model.Fornecedor;
import com.carcara.oracle.kitchencloud.model.Ingrediente;
import com.carcara.oracle.kitchencloud.model.ItemCompra;
import com.carcara.oracle.kitchencloud.model.dto.CadastroCompraDTO;
import com.carcara.oracle.kitchencloud.model.dto.CadastroItemCompraDTO;
import com.carcara.oracle.kitchencloud.repository.CompraRepository;
import com.carcara.oracle.kitchencloud.repository.FornecedorRepository;
import com.carcara.oracle.kitchencloud.repository.IngredienteRepository;
import com.carcara.oracle.kitchencloud.repository.ItemCompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.List;

@Service
public class CompraService {
    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private ItemCompraRepository itemCompraRepository;

    @Autowired
    private FornecedorRepository fornecedorRepository;

    @Autowired
    private IngredienteRepository ingredienteRepository;
    public void cadastrarCompra(CadastroCompraDTO cadastroCompraDTO) {
        Optional<Fornecedor> fornecedor = fornecedorRepository.findById(cadastroCompraDTO.idFornecedor());

        if(fornecedor.isEmpty()){

        }
        Compra compra = new Compra(cadastroCompraDTO,fornecedor.get());

        compra.setCodCompra(compraRepository.findFirstByOrderByIdDesc()+1);

        compraRepository.save(compra);

        List<ItemCompra> itemCompras = new ArrayList<>();

        Long itemCompraIdStart = itemCompraRepository.findFirstByOrderByIdDesc()+1;
        for(CadastroItemCompraDTO itens : cadastroCompraDTO.itens()){
            Optional<Ingrediente> ingrediente = ingredienteRepository.findById(itens.codIngrediente());
            if(ingrediente.isEmpty()){

            }
            ItemCompra itemCompra = new ItemCompra(itens,ingrediente.get(),fornecedor.get(),compra);
            itemCompra.setCodItemCompra(itemCompraIdStart);
            itemCompras.add(itemCompra);
            itemCompraIdStart++;
        }

        itemCompraRepository.saveAll(itemCompras);
    }

}
