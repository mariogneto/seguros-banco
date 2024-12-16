package br.com.mgn.segurosbanco.service.dto;

import java.time.LocalDate;

public record ClienteDTO(String cpf, String nome, LocalDate dataNascimento, String telefone, EnderecoDTO endereco) {}
