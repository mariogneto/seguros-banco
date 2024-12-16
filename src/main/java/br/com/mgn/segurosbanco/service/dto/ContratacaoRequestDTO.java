package br.com.mgn.segurosbanco.service.dto;

import br.com.mgn.segurosbanco.entity.TipoSeguro;

public record ContratacaoRequestDTO(String cpfCliente, TipoSeguro tipoSeguro) {
}
