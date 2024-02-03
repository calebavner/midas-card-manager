package ms.cartoes.controller;

import lombok.RequiredArgsConstructor;
import ms.cartoes.domain.Cartao;
import ms.cartoes.domain.CartoesPorCliente;
import ms.cartoes.service.CartaoService;
import ms.cartoes.service.ClienteCartaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cartoes")
@RequiredArgsConstructor
public class CartaoController {

    private final CartaoService cartaoService;
    private final ClienteCartaoService clienteCartaoService;

    @PostMapping
    public ResponseEntity<Cartao> gerarCartao(@RequestBody Cartao c) {
        cartaoService.gerarCartao(c);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<Cartao>> listarCartoesPorRenda(@RequestParam Long renda) {
        return ResponseEntity.ok(cartaoService.listarCartoesRendaMenorIgual(renda));
    }
    @GetMapping(params = "cpf")
    public ResponseEntity<List<CartoesPorCliente>> listarCartoesPorCpf(String cpf) {
        List<CartoesPorCliente> result = clienteCartaoService.listarCartoesPorCpf(cpf)
                                    .stream()
                                    .map(CartoesPorCliente::fromModel)
                                    .toList();

        return ResponseEntity.ok(result);
    }
}
