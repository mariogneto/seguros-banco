package br.com.mgn.segurosbanco.service;

import br.com.mgn.segurosbanco.domain.ClienteDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class ClienteService {

    private final String URL = "http://localhost:8080/clientes/{cpf}";
    private final RestTemplate restTemplate;

    public ClienteService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ClienteDTO buscarClientePorCpf(String cpfCliente) {
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("cpf", cpfCliente);
        ResponseEntity<ClienteDTO> response = restTemplate.getForEntity(URL, ClienteDTO.class, uriVariables);
        return response.getBody();
    }
}
