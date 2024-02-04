package ms.cartoes;

import ms.cartoes.domain.Cartao;
import ms.cartoes.domain.enums.BandeiraCartao;
import ms.cartoes.repo.CartaoRepository;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
@EnableDiscoveryClient
@EnableRabbit
public class MsCartoesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsCartoesApplication.class, args);
	}
}
