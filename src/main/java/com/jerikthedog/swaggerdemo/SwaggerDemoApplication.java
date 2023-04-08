package com.jerikthedog.swaggerdemo;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// modifies the swagger UI - documentation basically + some functional stuff
// the main usefulness still eludes me, but ok
@OpenAPIDefinition(
		info = @Info(
				title= "TodoItems API",
				version = "0.0.1.CAKE"
		),
		servers = {
				@Server(
						url = "http://localhost:8080"
				),
				@Server(
						url = "http://jerik-the-dog.wuf:1234"
				),
		},
		security = @SecurityRequirement(
				name = "require sweets",
				scopes = {"candy", "lolly"}
		)
)
public class SwaggerDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SwaggerDemoApplication.class, args);
	}




}
