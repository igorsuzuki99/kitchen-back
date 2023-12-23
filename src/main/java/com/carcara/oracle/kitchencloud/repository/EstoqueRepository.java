package com.carcara.oracle.kitchencloud.repository;

import com.carcara.oracle.kitchencloud.model.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EstoqueRepository extends JpaRepository<Estoque, Long> {

    @Query(value = "SELECT cod_estoque\n" +
            "FROM (\n" +
            "    SELECT cod_estoque\n" +
            "    FROM tb_estoque\n" +
            "    ORDER BY cod_estoque DESC\n" +
            ")\n" +
            "WHERE ROWNUM = 1",nativeQuery = true)
    Long findFirstByOrderByIdDesc();

    @Query(value = "SELECT * FROM TB_ESTOQUE WHERE DATA_VALIDADE < ?1 AND QUANTIDADE_PRODUTO > 0",nativeQuery = true)
    List<Estoque> procurarEstoquePorDataValidade(LocalDate validade);
}
