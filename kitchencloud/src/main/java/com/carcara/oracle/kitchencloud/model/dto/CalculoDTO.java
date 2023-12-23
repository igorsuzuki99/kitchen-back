package com.carcara.oracle.kitchencloud.model.dto;

import java.math.BigDecimal;
import java.time.Duration;

public record CalculoDTO(BigDecimal precoMedioNota, BigDecimal receitaTotal, Duration permancencia, Integer totalNota) {


}
