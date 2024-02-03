package msclientes;

import msclientes.domain.Cliente;
import msclientes.repo.ClienteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class MsClientesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsClientesApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner(ClienteRepository repo) {
		return args -> repo.save(new Cliente("03839947561", "Avner Caleb", 35));
	}
}
