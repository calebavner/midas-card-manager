package ms.avaliador.infra.mq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import ms.avaliador.domain.DadosEmissaoCartao;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmissaoCartaoPublisher {

    private final RabbitTemplate template;
    private final Queue filaEmissao;

    public void solicitarCartao(DadosEmissaoCartao dados) throws JsonProcessingException{
        String json = toJson(dados);
        template.convertAndSend(filaEmissao.getName(), json);
    }

    private String toJson(DadosEmissaoCartao dados) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(dados);
        return json;
    }
}
