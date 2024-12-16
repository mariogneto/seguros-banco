package br.com.mgn.segurosbanco.service;

import br.com.mgn.segurosbanco.controller.dto.SimulacaoRequestDTO;
import br.com.mgn.segurosbanco.entity.TipoSeguro;
import br.com.mgn.segurosbanco.service.dto.ContratacaoRequestDTO;
import br.com.mgn.segurosbanco.service.dto.SeguroDTO;
import br.com.mgn.segurosbanco.service.dto.SimulacaoSeguroDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class SeguroService {

    //private final ClienteService clienteService;

   /* public SeguroService(ClienteService clienteService) {
        this.clienteService = clienteService;
    }*/

    public SimulacaoSeguroDTO simularSeguro(SimulacaoRequestDTO simulacaoRequest) {
       //ClienteDTO cliente = clienteService.buscarClientePorCpf(simulacaoRequest.getCpfCliente());

        SimulacaoSeguroDTO simulacao = new SimulacaoSeguroDTO(new BigDecimal("100.00"), new BigDecimal("200.00"), new BigDecimal("300.00"));

        return simulacao;
    }

    @CircuitBreaker(name = "clienteService", fallbackMethod = "fallbackBuscarCliente")
    public Object buscarCliente(String cpf) {
        // chamada HTTP para API de Cadastro
    }


    public SeguroDTO contratarSeguro(ContratacaoRequestDTO contratacaoRequest) {
        //ClienteDTO cliente = clienteService.buscarClientePorCpf(contratacaoRequest.getCpfCliente());

        SeguroDTO seguro = new SeguroDTO("11122233312", TipoSeguro.OURO, obterValorSeguro(TipoSeguro.OURO), LocalDate.now() );
       /* seguro.cpfCliente(cliente.getCpf());
        seguro.tipoSeguro(contratacaoRequest.getTipoSeguro());
        seguro.valorContratado(obterValorSeguro(contratacaoRequest.getTipoSeguro()));
        seguro.dataContratacao(LocalDate.now());*/

        // Salvar no banco ou enviar evento para processar contratação
        return seguro;
    }

    private BigDecimal obterValorSeguro(TipoSeguro tipoSeguro) {
        switch (tipoSeguro) {
            case BRONZE:
                return new BigDecimal("100.00");
            case PRATA:
                return new BigDecimal("200.00");
            case OURO:
                return new BigDecimal("300.00");
            default:
                throw new IllegalArgumentException("Tipo de seguro inválido");
        }
    }
}



