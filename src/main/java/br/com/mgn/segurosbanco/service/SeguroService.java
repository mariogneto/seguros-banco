package br.com.mgn.segurosbanco.service;

import br.com.mgn.segurosbanco.domain.SimulacaoRequestDTO;
import br.com.mgn.segurosbanco.domain.Seguro;
import br.com.mgn.segurosbanco.domain.TipoSeguro;
import br.com.mgn.segurosbanco.domain.ClienteDTO;
import br.com.mgn.segurosbanco.domain.ContratacaoRequestDTO;
import br.com.mgn.segurosbanco.domain.SeguroDTO;
import br.com.mgn.segurosbanco.domain.SimulacaoSeguroDTO;
import br.com.mgn.segurosbanco.repository.SeguroRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class SeguroService {

    private final SeguroRepository seguroRepository;
    private final ClienteService clienteService;

    public SeguroService(SeguroRepository seguroRepository, ClienteService clienteService) {
        this.seguroRepository = seguroRepository;
        this.clienteService = clienteService;
    }

    public SimulacaoSeguroDTO simularSeguro(SimulacaoRequestDTO simulacaoRequest) throws Exception {
       ClienteDTO cliente = clienteService.buscarClientePorCpf(simulacaoRequest.cpfCliente());
        if (cliente == null) {
            //TODO colcor msg Simulação nao pode ser efetuada pois o cliente não encontrado.
            throw new Exception("Cliente nao encontrado");
        }
       SimulacaoSeguroDTO simulacao = new SimulacaoSeguroDTO(new BigDecimal("100.00"), new BigDecimal("200.00"), new BigDecimal("300.00"));

       return simulacao;
    }

    @CircuitBreaker(name = "clienteService", fallbackMethod = "fallbackBuscarCliente")
    public ClienteDTO buscarCliente(String cpf) {
        //TODO chamada HTTP para API de Cadastro
        ClienteDTO cliente = clienteService.buscarClientePorCpf(cpf);
        return cliente;
    }

    public SeguroDTO contratarSeguro(ContratacaoRequestDTO contratacaoRequest) throws Exception {
        ClienteDTO cliente = clienteService.buscarClientePorCpf(contratacaoRequest.cpfCliente());

        if (cliente == null) {
            //TODO colcor msg Contratação nao pode ser efetuada pois o cliente não encontrado.
            throw new Exception("Cliente nao encontrado");
        }

        SeguroDTO seguro = new SeguroDTO(cliente.cpf(), contratacaoRequest.tipoSeguro(), obterValorSeguro(contratacaoRequest.tipoSeguro()), LocalDate.now());
        seguroRepository.save(mapToEntity(seguro));
        return seguro;
    }

    private BigDecimal obterValorSeguro(TipoSeguro tipoSeguro) {
        return switch (tipoSeguro) {
            case BRONZE -> new BigDecimal("100.00");
            case PRATA -> new BigDecimal("200.00");
            case OURO -> new BigDecimal("300.00");
            default -> throw new IllegalArgumentException("Tipo de seguro inválido");
        };
    }

    private Seguro mapToEntity(@NotNull SeguroDTO seguroDTO) {
        var seguro = new Seguro();
        seguro.setCpfCliente(seguroDTO.cpfCliente());
        seguro.setTipoSeguro(seguroDTO.tipoSeguro());
        seguro.setDataContratacao(seguroDTO.dataContratacao());
        seguro.setValorContratado(seguroDTO.valorContratado());
        return seguro;
    }
}



