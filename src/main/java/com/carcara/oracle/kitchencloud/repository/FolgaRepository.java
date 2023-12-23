package com.carcara.oracle.kitchencloud.repository;

import com.carcara.oracle.kitchencloud.model.Folga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FolgaRepository extends JpaRepository<Folga, Long> {
}
