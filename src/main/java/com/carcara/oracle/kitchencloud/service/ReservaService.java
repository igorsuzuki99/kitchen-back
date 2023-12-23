package com.carcara.oracle.kitchencloud.service;

import com.carcara.oracle.kitchencloud.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    public Object quantidadeReservas(LocalDateTime data1, LocalDateTime data2){
        return reservaRepository.countByDataReservaBetween(data1, data2);
    }
}
