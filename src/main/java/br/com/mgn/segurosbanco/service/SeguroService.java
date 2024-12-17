package br.com.mgn.segurosbanco.service;

import br.com.mgn.segurosbanco.domain.SimulacaoRequestDTO;
import br.com.mgn.segurosbanco.domain.Seguro;
import br.com.mgn.segurosbanco.domain.TipoSeguro;
import br.com.mgn.segurosbanco.domain.ClienteDTO;
import br.com.mgn.segurosbanco.domain.ContratacaoRequestDTO;
import br.com.mgn.segurosbanco.domain.SeguroDTO;
import br.com.mgn.segurosbanco.domain.SimulacaoSeguroDTO;
import br.com.mgn.segurosbanco.exception.ClienteNotFoundException;
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

    public SimulacaoSeguroDTO simularSeguro(SimulacaoRequestDTO simulacaoRequest) {
       this.buscarCliente(simulacaoRequest.cpfCliente());
       return new SimulacaoSeguroDTO(new BigDecimal("100.00"), new BigDecimal("200.00"), new BigDecimal("300.00"));
    }

    public SeguroDTO contratarSeguro(ContratacaoRequestDTO contratacaoRequest) {
        ClienteDTO cliente = this.buscarCliente(contratacaoRequest.cpfCliente());
        SeguroDTO seguro = new SeguroDTO(cliente.cpf(), contratacaoRequest.tipoSeguro(), obterValorSeguro(contratacaoRequest.tipoSeguro()), LocalDate.now());
        seguroRepository.save(mapToEntity(seguro));
        return seguro;
    }

    @CircuitBreaker(name = "myService", fallbackMethod = "fallbackResponse")
    public ClienteDTO buscarCliente(String cpf) {
        return clienteService.buscarClientePorCpf(cpf);
    }

    //TODO não está dando fallback ao buscarCliente e dar erro
    private ClienteDTO fallbackResponse(String cpf, Throwable ex) {
        throw new ClienteNotFoundException(String.format("O Cliente %s não foi encontrado e não pode Executar a operação" , cpf), ex);
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



