package ms.cartoes.infra.mq;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import ms.cartoes.domain.Cartao;
import ms.cartoes.domain.ClienteCartao;
import ms.cartoes.domain.DadosEmissaoCartao;
import ms.cartoes.repo.CartaoRepository;
import ms.cartoes.repo.ClienteCartaoRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmissaoCartaoSubscriber {

    private final CartaoRepository cartaoRepository;
    private final ClienteCartaoRepository clienteCartaoRepository;
    @RabbitListener(queues = "${mq.queues.emissao-cartoes}")
    public void receberSolicitarEmissao(@Payload String payload) throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        DadosEmissaoCartao dados = mapper.readValue(payload, DadosEmissaoCartao.class);
        Cartao cartao = cartaoRepository.findById(dados.getIdCartao()).orElseThrow();

        ClienteCartao clienteCartao = new ClienteCartao();
        clienteCartao.setCartao(cartao);
        clienteCartao.setCpf(dados.getCpf());
        clienteCartao.setLimite(dados.getLimiteLiberado());
        clienteCartaoRepository.save(clienteCartao);
    }
}
