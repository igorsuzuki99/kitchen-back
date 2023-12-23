package com.carcara.oracle.kitchencloud.service;

import com.carcara.oracle.kitchencloud.model.Comanda;
import com.carcara.oracle.kitchencloud.model.Funcionario;
import com.carcara.oracle.kitchencloud.model.Nota;
import com.carcara.oracle.kitchencloud.model.dto.*;
import com.carcara.oracle.kitchencloud.repository.ComandaRepository;
import com.carcara.oracle.kitchencloud.repository.FuncionarioRepository;
import com.carcara.oracle.kitchencloud.repository.NotaRepository;
import jdk.jfr.Percentage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class FuncionarioService {


    @Autowired
    private FuncionarioRepository funcionarioRepository;
    @Autowired
    private ComandaRepository comandaRepository;
    @Autowired
    private NotaRepository notaRepository;

    public List<ExibicaoFuncionarioDTO> listarFuncionarios() {
        List<Funcionario> funcionarios = funcionarioRepository.findAll();
        return funcionarios.stream().map(funcionario -> new ExibicaoFuncionarioDTO(funcionario)).toList();
    }

    public ExibicaoFuncionarioDTO buscarFuncionario(Long id){
        Optional<Funcionario> funcionario = funcionarioRepository.findById(id);
        return new ExibicaoFuncionarioDTO(funcionario.get());
    }

    public CalculoAtendimentosDTO calculoTotalAtendimentos(Long codFuncionario, LocalDateTime data1, LocalDateTime data2) {
        Optional<Funcionario> funcionario = funcionarioRepository.findById(codFuncionario);
        if (funcionario.isEmpty()) {
        }
        Long atendimentosRealizados = comandaRepository.countByFuncionarioAndHorarioAberturaBetween(funcionario.get(), data1, data2);
        Long atendimentosTotal = comandaRepository.countByHorarioAberturaBetween(data1, data2);
        Long dias = ChronoUnit.DAYS.between(data1, data2);
        return new CalculoAtendimentosDTO(dias, atendimentosTotal, atendimentosRealizados);
    }

    public Map<String, Double> calculoRendimento(LocalDateTime data1, LocalDateTime data2) {
        List<Comanda> comandas = new ArrayList<>();

        if (data1 == null && data2 == null) {
            comandas = comandaRepository.findByHorarioAberturaBetween(LocalDateTime.now().minusDays(30), LocalDateTime.now());
        } else if (data1 != null && data2 == null) {
            comandas = comandaRepository.findByHorarioAberturaBetween(data1, data1);
        }else{
            comandas = comandaRepository.findByHorarioAberturaBetween(data1, data2);

        }

        Integer totalComandas = comandas.size();

        Map<String, Integer> quantidadeAtendimentoFunc = new HashMap();

        for (Comanda comanda : comandas) {
            String nomeFuncionario = comanda.getFuncionario().getNomeFuncionario();
            quantidadeAtendimentoFunc.put(nomeFuncionario, quantidadeAtendimentoFunc.getOrDefault(nomeFuncionario, 0) + 1);
        }

        Map<String, Double> porcentagemAtendimentoFunc = new HashMap();

        for (String funcionario : quantidadeAtendimentoFunc.keySet()) {
            int atendimentosFuncionario = quantidadeAtendimentoFunc.get(funcionario);
            double porcentagem = (double) atendimentosFuncionario / totalComandas * 100;
            porcentagemAtendimentoFunc.put(funcionario, porcentagem);
        }

        return porcentagemAtendimentoFunc;
    }

    public List<ExibicaoNotaDTO> avaliacoesFuncionario(Long codFuncionario){
        List<Nota> notas = notaRepository.findByFuncionarioCodFuncionario(codFuncionario);
        return notas.stream().map(nota -> new ExibicaoNotaDTO(nota)).toList();
    }

    public List<MediaAvaliacaoPorFuncionarioDTO> calculoMediaAvalicaoPorFuncionarioPorPeriodo(
            Long idFuncionario, LocalDateTime dataAvaliacaoIni, LocalDateTime dataAvaliacaoFin){
        List<MediaAvaliacaoPorFuncionarioDTO> mediaAvaliacaoPorFuncionarioDTOS = new ArrayList<>();
        List<Nota> notasPorRange =
                idFuncionario == null ? notaRepository.findBetweenDataAvaliacao(dataAvaliacaoIni,dataAvaliacaoFin) :
                        notaRepository.findBetweenDataAvaliacao(encontrarFuncionario(idFuncionario),dataAvaliacaoIni,dataAvaliacaoFin);

        Map<Funcionario, List<Nota>> avaliacoesPorFuncionario = notasPorRange.stream()
                .collect(Collectors.groupingBy(Nota::getFuncionario));

        avaliacoesPorFuncionario.forEach((funcionario, notas) -> {
            OptionalDouble media = notas.stream()
                    .mapToDouble(Nota::getNotaAtendimento)
                    .average();
            Long mediaComoLong = media.isPresent() ? Double.valueOf(media.getAsDouble()).longValue() : null;

            mediaAvaliacaoPorFuncionarioDTOS.add(new MediaAvaliacaoPorFuncionarioDTO(funcionario.getCodFuncionario(),
                    funcionario.getNomeFuncionario(),
                    mediaComoLong));
        });
        return mediaAvaliacaoPorFuncionarioDTOS;
    }

    public Funcionario encontrarFuncionario (Long codFuncionario){
        return funcionarioRepository.findById(codFuncionario).orElseThrow();
    }

    public List<ExibicaoFuncionarioDTO> funcionariosEscalados(LocalDateTime data) {
        String diaSemana = data.getDayOfWeek().getDisplayName(TextStyle.FULL, new Locale("pt", "BR"));
        List<Funcionario> funcionariosEscalados = funcionarioRepository.findFuncionariosEscaladosNoDia(diaSemana);
        return funcionariosEscalados.stream().map(funcionario -> new ExibicaoFuncionarioDTO(funcionario)).toList();
    }

}
