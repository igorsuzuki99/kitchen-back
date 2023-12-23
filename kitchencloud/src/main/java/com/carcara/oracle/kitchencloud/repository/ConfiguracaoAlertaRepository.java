package com.carcara.oracle.kitchencloud.repository;

import com.carcara.oracle.kitchencloud.model.ConfiguracaoAlerta;
import com.carcara.oracle.kitchencloud.model.enums.CondicaoDisparoAlerta;
import com.carcara.oracle.kitchencloud.model.enums.Entidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConfiguracaoAlertaRepository extends JpaRepository<ConfiguracaoAlerta, Integer> {
    @Query(value = "SELECT ID\n" +
            "FROM (\n" +
            "    SELECT ID\n" +
            "    FROM configuracao_alerta\n" +
            "    ORDER BY ID DESC\n" +
            ")\n" +
            "WHERE ROWNUM = 1",nativeQuery = true)
    Integer findFirstByOrderByIdDesc();

    List<ConfiguracaoAlerta> findByEntidade(Entidade estoque);

    List<ConfiguracaoAlerta> findByEntidadeAndCondicaoDisparo(Entidade estoque, CondicaoDisparoAlerta validade);
}