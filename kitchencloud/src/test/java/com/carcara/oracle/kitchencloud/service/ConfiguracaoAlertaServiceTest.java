package com.carcara.oracle.kitchencloud.service;

import com.carcara.oracle.kitchencloud.model.ConfiguracaoAlerta;
import com.carcara.oracle.kitchencloud.model.dto.CadastroConfiguracaoAlertaDTO;
import com.carcara.oracle.kitchencloud.repository.ConfiguracaoAlertaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class ConfiguracaoAlertaServiceTest {
    @Mock
    private ConfiguracaoAlertaRepository configuracaoAlertaRepository;

    @InjectMocks
    private ConfiguracaoAlertaService configuracaoAlertaService;

    CadastroConfiguracaoAlertaDTO cadastroConfiguracaoAlertaDTO;

    ConfiguracaoAlerta configuracaoAlerta;

//    @BeforeEach
//    public void setUp(){
//        cadastroConfiguracaoAlertaDTO = new CadastroConfiguracaoAlertaDTO(
//                "Alerta1","DescAlerta1","EntidadeAlerta1","" +
//                "Condicao1","Valor1","Acao1","dest@gmail.com"
//        );
//        configuracaoAlerta = new ConfiguracaoAlerta(cadastroConfiguracaoAlertaDTO);
//    }
//
//    @Test
//    public void criarAlertaOk(){
//        Mockito.when(configuracaoAlertaRepository.save(any(ConfiguracaoAlerta.class)))
//                .thenReturn(configuracaoAlerta);
//
//        ConfiguracaoAlerta configuracaoAlertaResposta = configuracaoAlertaService
//                .criarAlerta(cadastroConfiguracaoAlertaDTO);
//
//        Assertions.assertEquals(configuracaoAlerta,configuracaoAlertaResposta);
//
//        Mockito.verify(configuracaoAlertaRepository,Mockito.times(1)).findFirstByOrderByIdDesc();
//        Mockito.verify(configuracaoAlertaRepository,Mockito.times(1)).save(any(ConfiguracaoAlerta.class));
//    }
}
