package com.carcara.oracle.kitchencloud.repository;

import com.carcara.oracle.kitchencloud.model.ViewVendas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViewVendasRepository extends JpaRepository<ViewVendas, Long> {

    @Query(value = "SELECT * FROM V_VENDAS WHERE TO_NUMBER(TO_CHAR(DATA_VENDA, 'D')) = ?1 AND DATA_VENDA BETWEEN TO_DATE(?2, 'YYYY-MM-DD') AND TO_DATE(?3, 'YYYY-MM-DD')",nativeQuery = true)
    List<ViewVendas> vendasPorDataEdia (Integer diaSemana, String data1, String data2);
}
