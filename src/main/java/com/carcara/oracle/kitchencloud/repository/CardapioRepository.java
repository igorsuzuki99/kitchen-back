package com.carcara.oracle.kitchencloud.repository;

import com.carcara.oracle.kitchencloud.model.Cardapio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardapioRepository extends JpaRepository<Cardapio, Long> {
}
