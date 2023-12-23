package com.carcara.oracle.kitchencloud.repository;

import com.carcara.oracle.kitchencloud.model.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredienteRepository extends JpaRepository<Ingrediente,Long> {

    @Query(value = "SELECT cod_ingrediente\n" +
            "FROM (\n" +
            "    SELECT cod_ingrediente\n" +
            "    FROM tb_ingredientes\n" +
            "    ORDER BY cod_ingrediente DESC\n" +
            ")\n" +
            "WHERE ROWNUM = 1",nativeQuery = true)
    Long findFirstByOrderByIdDesc();

    @Query("SELECT i FROM Ingrediente i " +
            "WHERE i.quantidadeTotal < (i.estoqueMinimo + ((i.capacidade - i.estoqueMinimo) * 0.1))")
    List<Ingrediente> findIngredientesComQuantidadeAbaixoDoLimite();
}
