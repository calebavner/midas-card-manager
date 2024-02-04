package ms.avaliador.service;

import lombok.RequiredArgsConstructor;
import ms.avaliador.domain.*;
import ms.avaliador.infra.cartoes.CartoesResourceClient;
import ms.avaliador.infra.clientes.ClienteResourceClient;
import ms.avaliador.infra.mq.EmissaoCartaoPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AvaliadorCreditoService {

    private final ClienteResourceClient clienteResource;
    private final CartoesResourceClient cartaoResource;
    private final EmissaoCartaoPublisher emissaoCartaoPublisher;

    public SituacaoCliente obterSituacaoCliente(String cpf) {
        ResponseEntity<DadosCliente> cliente = clienteResource.buscarPorCpf(cpf);
        ResponseEntity<List<CartaoCliente>> c = cartaoResource.listarCartoesPorCpf(cpf);

        return SituacaoCliente.builder()
            .cliente(cliente.getBody())
            .cartoes(c.getBody())
            .build();
    }

    public RetornoAvaliacao realizarAvaliacao(String cpf, Long renda) {
        ResponseEntity<DadosCliente> cliente = clienteResource.buscarPorCpf(cpf);
        ResponseEntity<List<Cartao>> c = cartaoResource.listarCartoesPorRenda(renda);

        List<Cartao> cartoes = c.getBody();

        List<CartoaoAprovado> listaCartaoAprovados = cartoes.stream().map(cartao -> {
            DadosCliente dadosCliente = cliente.getBody();

            BigDecimal limiteBasico = cartao.getLimiteInicial();
            BigDecimal idadeBD = BigDecimal.valueOf(dadosCliente.getIdade());
            BigDecimal fator = idadeBD.divide(BigDecimal.valueOf(10));
            BigDecimal limiteAprovado = fator.multiply(limiteBasico);

            CartoaoAprovado cartaoAprovado = new CartoaoAprovado();
            cartaoAprovado.setCartao(cartao.getNome());
            cartaoAprovado.setBandeira(cartao.getBandeira());
            cartaoAprovado.setLimiteAprovado(limiteAprovado);

            return cartaoAprovado;
        }).collect(Collectors.toList());

        return new RetornoAvaliacao(listaCartaoAprovados);
    }

    public ProtocoloSolicitacaoCartao solicitarEmissaoCartao(DadosEmissaoCartao dados) throws Exception{
        emissaoCartaoPublisher.solicitarCartao(dados);
        return new ProtocoloSolicitacaoCartao(UUID.randomUUID().toString());
    }
}
