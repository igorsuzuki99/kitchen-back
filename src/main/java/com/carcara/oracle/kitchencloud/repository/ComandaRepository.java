package com.carcara.oracle.kitchencloud.repository;

import com.carcara.oracle.kitchencloud.model.Comanda;
import com.carcara.oracle.kitchencloud.model.Funcionario;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ComandaRepository extends JpaRepository<Comanda, Long> {

    List<Comanda> findByHorarioAberturaBetween(LocalDateTime dataInicio, LocalDateTime dataFim);
    long countByHorarioAberturaBetween(LocalDateTime dataInicio, LocalDateTime dataFim);

    long countByFuncionarioAndHorarioAberturaBetween(Funcionario funcionario, LocalDateTime dataInicio, LocalDateTime dataFim);


    List<Comanda> findByHorarioFechamentoBetween(LocalDateTime inicioSemana, LocalDateTime fimSemana);
}
