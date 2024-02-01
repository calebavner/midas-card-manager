package msclientes.controller;

import lombok.RequiredArgsConstructor;
import msclientes.domain.Cliente;
import msclientes.service.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService service;

    @PostMapping
    public ResponseEntity<Cliente> cadastrar(@RequestBody Cliente c, UriComponentsBuilder uriBuilder) {
        Cliente newCliente = service.cadastrar(c);
        URI uri = uriBuilder.path("/clientes/{cpf}").buildAndExpand(newCliente.getCpf()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity buscarPorCpf(@RequestParam String cpf) {
        if(service.buscarPorCpf(cpf).isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(service.buscarPorCpf(cpf));
        }
    }
}
