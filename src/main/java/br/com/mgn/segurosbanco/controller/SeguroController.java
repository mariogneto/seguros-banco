package br.com.mgn.segurosbanco.controller;

import br.com.mgn.segurosbanco.controller.dto.SimulacaoRequestDTO;
import br.com.mgn.segurosbanco.service.SeguroService;
import br.com.mgn.segurosbanco.service.dto.ContratacaoRequestDTO;
import br.com.mgn.segurosbanco.service.dto.SeguroDTO;
import br.com.mgn.segurosbanco.service.dto.SimulacaoSeguroDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/seguros")
public class SeguroController {

    private final SeguroService seguroService;

    public SeguroController(SeguroService seguroService) {
        this.seguroService = seguroService;
    }

    @PostMapping("/simular/{cpf}")
    public ResponseEntity<SimulacaoSeguroDTO> simularSeguro(@PathVariable String cpf) throws Exception {
        // Recebendo apenas o CPF, futuramente podemos adicionar outros parâmetros para simular o seguro
        SimulacaoSeguroDTO simulacao = seguroService.simularSeguro(new SimulacaoRequestDTO(cpf));
        return ResponseEntity.ok(simulacao);
    }

    @PostMapping("/contratar")
    public ResponseEntity<SeguroDTO> contratarSeguro(@RequestBody ContratacaoRequestDTO contratacaoRequest) throws Exception {
        SeguroDTO seguroContratado = seguroService.contratarSeguro(contratacaoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(seguroContratado);
    }
}

