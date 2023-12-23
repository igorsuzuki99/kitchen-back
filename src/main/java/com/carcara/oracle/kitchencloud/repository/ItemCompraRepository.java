package com.carcara.oracle.kitchencloud.repository;

import com.carcara.oracle.kitchencloud.model.ItemCompra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemCompraRepository extends JpaRepository<ItemCompra, Long> {
    @Query(value = "SELECT cod_item_compra\n" +
            "FROM (\n" +
            "    SELECT cod_item_compra\n" +
            "    FROM tb_item_compra\n" +
            "    ORDER BY cod_item_compra DESC\n" +
            ")\n" +
            "WHERE ROWNUM = 1",nativeQuery = true)
    Long findFirstByOrderByIdDesc();
}
