package com.carcara.oracle.kitchencloud.repository;

import com.carcara.oracle.kitchencloud.model.ViewComanda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViewComandaRepository extends JpaRepository<ViewComanda, Integer> {
}
