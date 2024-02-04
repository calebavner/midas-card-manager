package ms.avaliador.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    @Value("${mq.queues.emissao-cartoes}")
    private String filaEmissaoCartao;
    @Bean
    public Queue emissaoCartoes() {
        return new Queue(filaEmissaoCartao, true);
    }
}
