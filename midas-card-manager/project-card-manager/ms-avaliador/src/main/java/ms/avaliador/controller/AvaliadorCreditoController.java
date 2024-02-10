package ms.avaliador.controller;

import lombok.RequiredArgsConstructor;
import ms.avaliador.domain.DadosAvaliacao;
import ms.avaliador.domain.DadosEmissaoCartao;
import ms.avaliador.domain.ProtocoloSolicitacaoCartao;
import ms.avaliador.domain.SituacaoCliente;
import ms.avaliador.service.AvaliadorCreditoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping
    public ResponseEntity realizarAvaliacao(@RequestBody DadosAvaliacao dados) {
        return ResponseEntity.ok(service.realizarAvaliacao(dados.getCpf(), dados.getRenda()));
    }
    @PostMapping("/solicitar")
    public ResponseEntity solicitarCartao(@RequestBody DadosEmissaoCartao dadosEmissaoCartao) {
        try {
            ProtocoloSolicitacaoCartao p = service.solicitarEmissaoCartao(dadosEmissaoCartao);
            return ResponseEntity.ok(p);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
