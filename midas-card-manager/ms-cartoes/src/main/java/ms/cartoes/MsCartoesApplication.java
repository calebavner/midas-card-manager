package ms.cartoes;

import ms.cartoes.domain.Cartao;
import ms.cartoes.domain.enums.BandeiraCartao;
import ms.cartoes.repo.CartaoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
@EnableDiscoveryClient
public class MsCartoesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsCartoesApplication.class, args);
	}
	@Bean
	CommandLineRunner runner(CartaoRepository repo) {
		return args -> repo.save(new Cartao("BB Start", BandeiraCartao.MASTERCARD, BigDecimal.valueOf(3000), BigDecimal.valueOf(900)));
	}
}
