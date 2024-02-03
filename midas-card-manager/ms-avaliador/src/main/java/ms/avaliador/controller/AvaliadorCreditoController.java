package ms.avaliador.controller;

import lombok.RequiredArgsConstructor;
import ms.avaliador.domain.SituacaoCliente;
import ms.avaliador.service.AvaliadorCreditoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/avaliador")
@RequiredArgsConstructor
public class AvaliadorCreditoController {

    private final AvaliadorCreditoService service;
    @GetMapping
    public String status() {
        return "OK!";
    }

    @GetMapping(value = "consulta", params = "cpf")
    public ResponseEntity<SituacaoCliente> consultaSituacaoCliente(@RequestParam String cpf) {
        SituacaoCliente s = service.obterSituacaoCliente(cpf);
        return ResponseEntity.ok(s);
    }
}
