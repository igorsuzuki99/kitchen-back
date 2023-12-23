package com.carcara.oracle.kitchencloud.service;

import java.time.LocalDate;
import java.time.YearMonth;

public class InformacoesMes {
    private int numeroDoDia;
    private LocalDate primeiroDiaDoMes;
    private LocalDate ultimoDiaDoMes;

    public InformacoesMes() {
        LocalDate dataAtual = LocalDate.now().plusDays(1);
        YearMonth mesAtual = YearMonth.from(dataAtual);

        this.numeroDoDia = (dataAtual.getDayOfWeek().getValue() % 7) + 1;
        this.primeiroDiaDoMes = mesAtual.atDay(1);
        this.ultimoDiaDoMes = mesAtual.atEndOfMonth();
    }

    public int getNumeroDoDia() {
        return numeroDoDia;
    }

    public LocalDate getPrimeiroDiaDoMes() {
        return primeiroDiaDoMes;
    }

    public LocalDate getUltimoDiaDoMes() {
        return ultimoDiaDoMes;
    }
}
