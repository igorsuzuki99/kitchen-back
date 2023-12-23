package com.carcara.oracle.kitchencloud.repository;

import com.carcara.oracle.kitchencloud.model.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CompraRepository extends JpaRepository<Compra,Long> {
    @Query(value = "SELECT cod_compra\n" +
            "FROM (\n" +
            "    SELECT cod_compra\n" +
            "    FROM tb_compra\n" +
            "    ORDER BY cod_compra DESC\n" +
            ")\n" +
            "WHERE ROWNUM = 1",nativeQuery = true)
    Long findFirstByOrderByIdDesc();
}
