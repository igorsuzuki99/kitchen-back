package com.carcara.oracle.kitchencloud.service;

import com.carcara.oracle.kitchencloud.model.ViewEstoque;
import com.carcara.oracle.kitchencloud.repository.ViewEstoqueRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

@SpringBootTest
public class ViewEstoqueServiceTest {

    @Mock
    private ViewEstoqueRepository viewEstoqueRepository;

    @InjectMocks
    private ViewEstoqueService viewEstoqueService;

    ViewEstoque viewEstoque;

    @BeforeEach
    public void setUp(){
        viewEstoque = ViewEstoque.builder().codIngrediente(1L).nomeIngrediente("Ingrediente 1")
                .unidadeMedida("kg").categoriaIngrediente("Massa").quantidade(30)
                .capacidade(100D).estoqueMinimo(10D).build();
    }

    @Test
    public void listarViewEstoqueOk(){
        Mockito.when(viewEstoqueRepository.findAll()).thenReturn(Collections.singletonList(viewEstoque));

        List<ViewEstoque> viewEstoques = viewEstoqueService.listarViewEstoque();

        Assertions.assertEquals(Collections.singletonList(viewEstoque),viewEstoques);

        Mockito.verify(viewEstoqueRepository,Mockito.times(1)).findAll();
    }
}
