package ms.avaliador.service;

import lombok.RequiredArgsConstructor;
import ms.avaliador.domain.CartaoCliente;
import ms.avaliador.domain.DadosCliente;
import ms.avaliador.domain.SituacaoCliente;
import ms.avaliador.infra.cartoes.CartoesResourceClient;
import ms.avaliador.infra.clientes.ClienteResourceClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AvaliadorCreditoService {

    private final ClienteResourceClient clienteResource;
    private final CartoesResourceClient cartaoResource;

    public SituacaoCliente obterSituacaoCliente(String cpf) {
        ResponseEntity<DadosCliente> cliente = clienteResource.buscarPorCpf(cpf);
        ResponseEntity<List<CartaoCliente>> c = cartaoResource.listarCartoesPorCpf(cpf);

        return SituacaoCliente.builder()
            .cliente(cliente.getBody())
            .cartoes(c.getBody())
            .build();
    }
}
