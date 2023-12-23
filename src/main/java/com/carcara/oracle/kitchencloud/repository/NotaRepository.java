package com.carcara.oracle.kitchencloud.repository;

import com.carcara.oracle.kitchencloud.model.Funcionario;
import com.carcara.oracle.kitchencloud.model.Nota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface NotaRepository extends JpaRepository<Nota, Long> {

    List<Nota> findByFuncionarioCodFuncionario(Long codFuncionario);

    @Query("SELECT n FROM Nota n WHERE n.dataAvaliacao >= :dataAvaliacaoIni AND n.dataAvaliacao <= :dataAvaliacaoFin")
    List<Nota> findBetweenDataAvaliacao(@Param("dataAvaliacaoIni") LocalDateTime dataAvaliacaoIni, @Param("dataAvaliacaoFin") LocalDateTime dataAvaliacaoFin);

    @Query("SELECT n FROM Nota n WHERE n.funcionario = :funcionario AND n.dataAvaliacao >= :dataAvaliacaoIni AND n.dataAvaliacao <= :dataAvaliacaoFin")
    List<Nota> findBetweenDataAvaliacao(@Param("funcionario") Funcionario funcionario, @Param("dataAvaliacaoIni") LocalDateTime dataAvaliacaoIni, @Param("dataAvaliacaoFin") LocalDateTime dataAvaliacaoFin);
}
