package br.com.mgn.segurosbanco.service.dto;

import br.com.mgn.segurosbanco.entity.TipoSeguro;

import java.math.BigDecimal;
import java.time.LocalDate;

public record SeguroDTO(String cpfCliente, TipoSeguro tipoSeguro, BigDecimal valorContratado, LocalDate dataContratacao) {}
