package com.carcara.oracle.kitchencloud.repository;

import com.carcara.oracle.kitchencloud.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    @Query(value = "SELECT F.* FROM TB_FUNCIONARIO F\n" +
            "JOIN TB_FOLGA_FUNCIONARIO FF ON F.COD_FUNCIONARIO = FF.COD_FUNCIONARIO\n" +
            "WHERE F.COD_FUNCIONARIO \n" +
            "NOT IN (SELECT FF2.COD_FUNCIONARIO\n" +
            "            FROM TB_FOLGA_FUNCIONARIO FF2 JOIN TB_FOLGA FO ON FF2.COD_FOLGA = FO.COD_FOLGA\n" +
            "            WHERE LOWER(FO.DATA_FOLGA) = LOWER(:diaEscalado))", nativeQuery = true)
    List<Funcionario> findFuncionariosEscaladosNoDia(@Param("diaEscalado") String diaEscalado);

}
