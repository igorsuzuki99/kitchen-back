package com.carcara.oracle.kitchencloud.service;

import com.carcara.oracle.kitchencloud.model.*;
import com.carcara.oracle.kitchencloud.model.dto.CadastroEstoqueDTO;
import com.carcara.oracle.kitchencloud.model.dto.ExibicaoEstoqueDTO;
import com.carcara.oracle.kitchencloud.model.dto.ExibicaoSaidaEstoqueDTO;
import com.carcara.oracle.kitchencloud.model.dto.SaidaEstoqueDTO;
import com.carcara.oracle.kitchencloud.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EstoqueService {

    @Autowired
    private EstoqueRepository estoqueRepository;

    @Autowired
    private ItemCompraRepository itemCompraRepository;

    @Autowired
    private SaidaEstoqueRepository saidaEstoqueRepository;
    @Autowired
    private IngredienteRepository ingredienteRepository;

    @Autowired
    private CardapioRepository cardapioRepository;

    @Autowired
    @Lazy
    private ConfiguracaoAlertaService configuracaoAlertaService;


    public ExibicaoEstoqueDTO insercaoInsumoEstoque(CadastroEstoqueDTO cadastroEstoqueDTO){
        Optional<ItemCompra> itemCompra = itemCompraRepository.findById(cadastroEstoqueDTO.codItemCompra());
 
        if (itemCompra.isEmpty()){

        }
        Estoque estoque = new Estoque(cadastroEstoqueDTO,itemCompra.get());
        estoque.setCodEstoque(estoqueRepository.findFirstByOrderByIdDesc()+1);
        adicionarNoBanco(itemCompra.get().getIngrediente(), estoque.getQuantidadeProduto());
        estoqueRepository.save(estoque);
        itemCompra.get().setStatusItem('R');
        itemCompraRepository.save(itemCompra.get());
        return new ExibicaoEstoqueDTO(estoque);
    }

    public ExibicaoSaidaEstoqueDTO saidaInsumoEstoque(SaidaEstoqueDTO saidaEstoqueDTO){
        Optional<Estoque> estoque = estoqueRepository.findById(saidaEstoqueDTO.codEstoque());
        if(estoque.isEmpty()){
            // AQUI COLOCAR EXCESSÃO
        }
        Optional<Cardapio> cardapio = cardapioRepository.findById(saidaEstoqueDTO.codPrato());
        if(cardapio.isEmpty()){
            // AQUI COLOCAR EXCESSÃO
        }
        SaidaEstoque saidaEstoque = new SaidaEstoque(saidaEstoqueDTO,estoque.get(),cardapio.get());
        retirarDoBanco(estoque.get().getItemCompra().getIngrediente(), saidaEstoqueDTO.quantidadeSaida());
        saidaEstoque.setCodSaida(saidaEstoqueRepository.findFirstByOrderByIdDesc());
        estoque.get().setQuantidadeProduto(estoque.get().getQuantidadeProduto()-saidaEstoqueDTO.quantidadeSaida());
        estoqueRepository.save(estoque.get());
        saidaEstoqueRepository.save(saidaEstoque);
        List<ConfiguracaoAlerta> alertasEstoque = configuracaoAlertaService.procurarAlertaEstoque();
        for(ConfiguracaoAlerta alerta : alertasEstoque){
            configuracaoAlertaService.alertaEstoque(estoque.get(),alerta);
        }
        return new ExibicaoSaidaEstoqueDTO(saidaEstoque);
    }

    public void retirarDoBanco(Ingrediente ingrediente, Integer quantidadeSaida){
        Float quantidadeAtual = ingrediente.getQuantidadeTotal();
        ingrediente.setQuantidadeTotal(quantidadeAtual - quantidadeSaida);
        ingredienteRepository.save(ingrediente);
    }

    public void adicionarNoBanco(Ingrediente ingrediente, Integer quantidadeSaida){
        Float quantidadeAtual = ingrediente.getQuantidadeTotal();
        ingrediente.setQuantidadeTotal(quantidadeAtual + quantidadeSaida);
        ingredienteRepository.save(ingrediente);
    }

    public List<ExibicaoEstoqueDTO> procurarEstoquePorDataValidadeDto(LocalDate validade){
        List<Estoque> estoques = estoqueRepository.procurarEstoquePorDataValidade(validade);
        return estoques.stream().map(estoque -> new ExibicaoEstoqueDTO(estoque)).toList();
    }

    public List<Estoque> procurarEstoquePorDataValidade(LocalDate validade){
        List<Estoque> estoques = estoqueRepository.procurarEstoquePorDataValidade(validade);
        return estoques;
    }


}
