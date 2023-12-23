package com.carcara.oracle.kitchencloud.repository;

import com.carcara.oracle.kitchencloud.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    Object countByDataReservaBetween(LocalDateTime data1, LocalDateTime data2);
}
