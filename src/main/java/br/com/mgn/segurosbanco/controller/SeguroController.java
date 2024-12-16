package br.com.mgn.segurosbanco.controller;

import br.com.mgn.segurosbanco.controller.dto.SimulacaoRequestDTO;
import br.com.mgn.segurosbanco.service.SeguroService;
import br.com.mgn.segurosbanco.service.dto.ContratacaoRequestDTO;
import br.com.mgn.segurosbanco.service.dto.SeguroDTO;
import br.com.mgn.segurosbanco.service.dto.SimulacaoSeguroDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seguros")
public class SeguroController {

    private final SeguroService seguroService;

    public SeguroController(SeguroService seguroService) {
        this.seguroService = seguroService;
    }

    @PostMapping("/simular")
    public ResponseEntity<SimulacaoSeguroDTO> simularSeguro(@RequestBody SimulacaoRequestDTO simulacaoRequest) {
        SimulacaoSeguroDTO simulacao = seguroService.simularSeguro(simulacaoRequest);
        return ResponseEntity.ok(simulacao);
    }

    @PostMapping("/contratar")
    public ResponseEntity<SeguroDTO> contratarSeguro(@RequestBody ContratacaoRequestDTO contratacaoRequest) {
        SeguroDTO seguroContratado = seguroService.contratarSeguro(contratacaoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(seguroContratado);
    }
}

