package com.carcara.oracle.kitchencloud.service;

import com.carcara.oracle.kitchencloud.model.Comanda;
import com.carcara.oracle.kitchencloud.model.Funcionario;
import com.carcara.oracle.kitchencloud.model.Nota;
import com.carcara.oracle.kitchencloud.model.dto.ExibicaoNotaDTO;
import com.carcara.oracle.kitchencloud.model.dto.MediaAvaliacaoPorFuncionarioDTO;
import com.carcara.oracle.kitchencloud.repository.ComandaRepository;
import com.carcara.oracle.kitchencloud.repository.NotaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class FuncionarioServiceTest {

    @Mock
    private ComandaRepository comandaRepository;
    @Mock
    private NotaRepository notaRepository;
    @InjectMocks
    private FuncionarioService funcionarioService;

    Comanda comanda1 = new Comanda();
    Comanda comanda2 = new Comanda();
    Funcionario funcionario1 = new Funcionario();
    Funcionario funcionario2 = new Funcionario();
    List<Comanda> comandas = new ArrayList<>();
    Map<String, Double> resultadoEsperado = new HashMap<>();
    Map<String, Double> result = new HashMap<>();
    Nota nota = new Nota();
    ExibicaoNotaDTO exibicaoNotaDTO;
    private LocalDateTime data1;
    private LocalDateTime data2;

    List<Nota> notasPorRange;

    List<MediaAvaliacaoPorFuncionarioDTO> mediaAvaliacao = new ArrayList<>();


    @BeforeEach
    public void setUp() {
        funcionario1.setCodFuncionario(1L);
        funcionario1.setNomeFuncionario("funcionario1");

        funcionario2.setCodFuncionario(2L);
        funcionario2.setNomeFuncionario("funcionario2");

        comanda1.setFuncionario(funcionario1);
        comanda2.setFuncionario(funcionario2);

        comandas.add(comanda1);
        comandas.add(comanda2);

        nota.setCodNota(1L);
        nota.setFuncionario(funcionario1);
        nota.setNotaAtendimento(10);
        nota.setDataAvaliacao(LocalDateTime.now().withNano(0));
        nota.setComentario("teste");

        exibicaoNotaDTO = new ExibicaoNotaDTO(nota);

        notasPorRange = new ArrayList<>();
        notasPorRange.add(nota);
        mediaAvaliacao.add(new MediaAvaliacaoPorFuncionarioDTO(nota.getFuncionario().getCodFuncionario(),
                nota.getFuncionario().getNomeFuncionario(),
                Long.valueOf(nota.getNotaAtendimento())));
    }

    @Test
    public void calculoRendimentoData1AndData2NullTest() {
        data1 = LocalDateTime.now().withNano(0);
        data2 = LocalDateTime.now().minusDays(30);

        when(comandaRepository.findByHorarioAberturaBetween(data1, data2)).thenReturn(comandas);

        resultadoEsperado.put(funcionario1.getNomeFuncionario(), 50D);
        resultadoEsperado.put(funcionario2.getNomeFuncionario(), 50D);

        result = funcionarioService.calculoRendimento(data1, data2);

        assertEquals(comandas.size(), result.size());
        assertEquals(resultadoEsperado, result);
    }

    @Test
    public void calculoRendimentoData2NullTest(){
        data1 = LocalDateTime.of(2023, 10, 29, 15, 30);
        data2 = null;

        comanda1.setHorarioAbertura(Timestamp.valueOf(data1));

        comanda2.setHorarioAbertura(Timestamp.valueOf(LocalDateTime.now().withNano(0)));
        when(comandaRepository.findByHorarioAberturaBetween(data1, data1)).thenReturn(Collections.singletonList(comanda1));

        resultadoEsperado.put(funcionario1.getNomeFuncionario(), 100D);

        result = funcionarioService.calculoRendimento(data1, data2);

        assertEquals(resultadoEsperado.size(), result.size());
        assertEquals(resultadoEsperado, result);
    }

    @Test
    public void calculoRendimentoData1AndData2NotNullTest(){
        data1 = LocalDateTime.of(2023, 10, 29, 15, 30);
        data2 = LocalDateTime.of(2023, 11, 29, 15, 30);

        comanda1.setHorarioAbertura(Timestamp.valueOf(data1));
        comanda2.setHorarioAbertura(Timestamp.valueOf(data2));

        when(comandaRepository.findByHorarioAberturaBetween(data1, data2)).thenReturn(comandas);

        resultadoEsperado.put(funcionario1.getNomeFuncionario(), 50D);
        resultadoEsperado.put(funcionario2.getNomeFuncionario(), 50D);

        result = funcionarioService.calculoRendimento(data1, data2);

        assertEquals(resultadoEsperado.size(), result.size());
        assertEquals(resultadoEsperado, result);
    }

    @Test
    public void avaliacoesFuncionario(){
        when(notaRepository.findByFuncionarioCodFuncionario(1L)).thenReturn(Collections.singletonList(nota));

        List<ExibicaoNotaDTO> exibicaoNotaDTOS = funcionarioService.avaliacoesFuncionario(1L);

        assertEquals("teste", nota.getComentario());
        assertTrue(exibicaoNotaDTOS.contains(exibicaoNotaDTO));
    }

    @Test
    public void calculoMediaAvalicaoPorFuncionarioPorPeriodoOk (){
        LocalDateTime dataAvaliacaoIni = LocalDateTime.now();
        when(notaRepository
                .findBetweenDataAvaliacao(any(LocalDateTime.class),any(LocalDateTime.class))).thenReturn(notasPorRange);

        List<MediaAvaliacaoPorFuncionarioDTO> mediaAvaliacaoPorFuncionarioDTOS
                = funcionarioService.calculoMediaAvalicaoPorFuncionarioPorPeriodo(null,dataAvaliacaoIni,dataAvaliacaoIni);

        assertEquals(mediaAvaliacaoPorFuncionarioDTOS,mediaAvaliacao);
    }


}


