package com.carcara.oracle.kitchencloud.service;

import com.carcara.oracle.kitchencloud.model.Comanda;
import com.carcara.oracle.kitchencloud.model.Funcionario;
import com.carcara.oracle.kitchencloud.model.VendaPrato;
import com.carcara.oracle.kitchencloud.model.dto.DadosExibicaoAtendimentosAgrupadosSemanaDia;
import com.carcara.oracle.kitchencloud.model.dto.VendaDiaSemanaDTO;
import com.carcara.oracle.kitchencloud.repository.ComandaRepository;
import com.carcara.oracle.kitchencloud.repository.ItemVendaDiariaRepository;
import com.carcara.oracle.kitchencloud.repository.VendaPratoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AtendimentoService {

    @Autowired
    private ComandaRepository comandaRepository;

    @Autowired
    private ItemVendaDiariaRepository itemVendaDiariaRepository;

    @Autowired
    private VendaPratoRepository vendaPratoRepository;
    public DadosExibicaoAtendimentosAgrupadosSemanaDia atendimentoDuranteDia(LocalDateTime data){
        LocalDateTime inicioSemana = data.with(DayOfWeek.MONDAY);

        LocalDateTime fimSemana = data.with(DayOfWeek.SUNDAY);
        List<Comanda> comandasDia = comandaRepository.findByHorarioFechamentoBetween(data,data.plusDays(1));
        List<Comanda> comandasSemana = comandaRepository.findByHorarioFechamentoBetween(inicioSemana,fimSemana);


        List<VendaPrato> pratosVendidos = pratosMaisVendidos(data);


        return new DadosExibicaoAtendimentosAgrupadosSemanaDia(
                agruparAtendimentosPorDia(comandasSemana),
                agruparAtendimentosPorPeriodo(comandasDia),
                agruparPorFuncionarioECargo(comandasDia),
                pratosVendidos);
    }

    public List<VendaPrato> pratosMaisVendidos (LocalDateTime data){
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dataFormatada = data.format(formatador);
        List<VendaPrato> pratosVendidos = vendaPratoRepository.findByDate(dataFormatada);
        return pratosVendidos;
    }

    public Map<String, Long> agruparAtendimentosPorDia(List<Comanda> comandas){
        Map<String, Long> todosDiasSemana = new HashMap<>();
        for (DayOfWeek dia : DayOfWeek.values()) {
            todosDiasSemana.put(dia.getDisplayName(TextStyle.FULL, new Locale("pt", "BR")), 0L);
        }
        Map<String, Long> agrupamento = comandas.stream()
                .collect(Collectors.groupingBy(comanda -> {

                    Timestamp timestamp = comanda.getHorarioFechamento(); // Supondo que o horário de fechamento seja um Timestamp

                    LocalDateTime localDateTime = timestamp.toLocalDateTime(); // Convertendo para LocalDateTime

                    return localDateTime.toLocalDate().getDayOfWeek().getDisplayName(TextStyle.FULL, new Locale("pt","BR")); // Obtendo a data para agrupar por dia
                }, Collectors.counting())); // Contando o número de comandas para cada dia
        for (String diaSemana : todosDiasSemana.keySet()) {
            if (!agrupamento.containsKey(diaSemana)) {
                agrupamento.put(diaSemana, 0L);
            }
        }
        return agrupamento;
    }

    public Map<String, String> agruparPorFuncionarioECargo(List<Comanda> comandas){
        Map<String, String> agrupamento = comandas.stream()
                .collect(Collectors.toMap(
                        comanda -> comanda.getFuncionario().getNomeFuncionario(), // Chave: nome do funcionário
                        comanda -> comanda.getFuncionario().getCargo(), // Valor: cargo do funcionário
                        (cargo1, cargo2) -> cargo1)); // Caso haja chaves duplicadas, mantém o primeiro cargo

        return agrupamento;
    }


    public Map<LocalTime, Long> agruparAtendimentosPorPeriodo(List<Comanda> comandas){
        Map<LocalTime, Long> agrupamento = comandas.stream()
                .collect(Collectors.groupingBy(comanda -> {
                    Timestamp timestamp = comanda.getHorarioFechamento(); // Supondo que a hora de fechamento seja um Timestamp

                    LocalDateTime localDateTime = timestamp.toLocalDateTime(); // Convertendo para LocalDateTime

                    comanda.getFuncionario().getCargo();
                    return localDateTime.toLocalTime().truncatedTo(ChronoUnit.HOURS); // Obtendo a hora e truncando para a hora mais próxima
                }, Collectors.counting())); // Contando o número de comandas para cada hora de fechamento
        return agrupamento;
    }
}
