package br.com.mgn.segurosbanco.service;

import br.com.mgn.segurosbanco.domain.*;
import br.com.mgn.segurosbanco.repository.SeguroRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;


import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SeguroServiceTest {

    @Mock
    private SeguroRepository seguroRepository;

    @Mock
    private ClienteService clienteService;

    @InjectMocks
    private SeguroService seguroService;

    private void carregaClienteMock() {
        when(clienteService.buscarClientePorCpf(anyString())).thenReturn(new ClienteDTO("1234567890", "Nome", LocalDate.now(), "", null));
    }

    @Test
    void deveSimularSeguro() {
        carregaClienteMock();
        SimulacaoSeguroDTO simulacaoSeguroDTO = seguroService.simularSeguro(new SimulacaoRequestDTO("1234567890"));
        assertTrue(simulacaoSeguroDTO.valorBronze().compareTo(new BigDecimal("100.00")) == 0);
        verify(clienteService, times(1)).buscarClientePorCpf(any(String.class));

    }

    @Test
    void deveContratarSeguroComTipoSeguro() {
        carregaClienteMock();
        SeguroDTO seguroDTO = seguroService.contratarSeguro(new ContratacaoRequestDTO("1234567890", TipoSeguro.BRONZE));
        assertTrue(seguroDTO.valorContratado().compareTo(new BigDecimal("100.00")) == 0);
        verify(seguroRepository, times(1)).save(any(Seguro.class));
    }

}