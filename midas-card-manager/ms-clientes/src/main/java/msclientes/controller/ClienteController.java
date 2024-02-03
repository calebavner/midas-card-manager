package msclientes.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import msclientes.domain.Cliente;
import msclientes.service.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
@Slf4j
public class ClienteController {

    private final ClienteService service;

    @PostMapping
    public ResponseEntity<Cliente> cadastrar(@RequestBody @Valid Cliente c, UriComponentsBuilder uriBuilder) {
        Cliente newCliente = service.cadastrar(c);
        URI uri = uriBuilder.path("/clientes/{cpf}").buildAndExpand(newCliente.getCpf()).toUri();


        log.info("Cliente cadastrado com sucesso!!!" + uri);
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity buscarPorCpf(@RequestParam @Valid String cpf) {
        if(service.buscarPorCpf(cpf).isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(service.buscarPorCpf(cpf));
        }
    }
}
