package br.com.mgn.segurosbanco.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

public record SeguroDTO(String cpfCliente, TipoSeguro tipoSeguro, BigDecimal valorContratado, LocalDate dataContratacao) {}
