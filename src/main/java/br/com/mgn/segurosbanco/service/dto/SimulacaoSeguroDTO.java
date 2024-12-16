package br.com.mgn.segurosbanco.service.dto;

import java.math.BigDecimal;

public record SimulacaoSeguroDTO(BigDecimal valorBronze, BigDecimal valorPrata, BigDecimal valorOuro) {}
