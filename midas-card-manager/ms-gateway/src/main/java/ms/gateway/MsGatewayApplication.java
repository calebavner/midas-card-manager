package ms.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class MsGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsGatewayApplication.class, args);
	}

	@Bean
	public RouteLocator router(RouteLocatorBuilder builder) {
		return builder
			.routes()
			.route(r -> r.path("/clientes/**").uri("lb://ms-clientes"))
			.route(r -> r.path("/cartoes/**").uri("lb://ms-cartoes"))
			.route(r -> r.path("/avaliador/**").uri("lb://ms-avaliador"))
			.build();
	}
}
