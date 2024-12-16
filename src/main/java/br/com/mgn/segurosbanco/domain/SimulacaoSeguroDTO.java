package br.com.mgn.segurosbanco.domain;

import java.math.BigDecimal;

public record SimulacaoSeguroDTO(BigDecimal valorBronze, BigDecimal valorPrata, BigDecimal valorOuro) {}
