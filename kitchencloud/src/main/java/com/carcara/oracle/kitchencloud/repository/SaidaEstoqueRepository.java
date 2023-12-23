package com.carcara.oracle.kitchencloud.repository;


import com.carcara.oracle.kitchencloud.model.SaidaEstoque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SaidaEstoqueRepository extends JpaRepository<SaidaEstoque, Long> {
    @Query(value = "SELECT cod_saida\n" +
            "FROM (\n" +
            "    SELECT cod_saida\n" +
            "    FROM tb_saida_estoque\n" +
            "    ORDER BY cod_saida DESC\n" +
            ")\n" +
            "WHERE ROWNUM = 1",nativeQuery = true)
    Long findFirstByOrderByIdDesc();
}
