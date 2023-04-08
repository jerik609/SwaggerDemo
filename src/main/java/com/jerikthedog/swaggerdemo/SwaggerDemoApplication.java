package com.jerikthedog.swaggerdemo;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// modifies the swagger UI - documentation basically + some functional stuff
// the main usefulness still eludes me, but ok
@OpenAPIDefinition(
		info = @Info( // description of this API
				title= "TodoItems API",
				version = "0.0.1.CAKE"
		),
		servers = { // definition of servers supporting the endpoints (why is this useful?)
				@Server(
						url = "http://localhost:8080"
				),
				@Server(
						url = "http://jerik-the-dog.wuf:1234"
				),
		},
//		security = @SecurityRequirement(
//				name = "require sweets",
//				scopes = {"candy", "lolly"}
//		),
		tags = { // allows us to name the tags we defined at the endpoints, the name must match the tag name in @Operation(s)
				@Tag(name = "TodoItems", description = "All the operations for TODOs"),
				@Tag(name = "Modifiers", description = "These endpoints are used to modify the TODOs")
		}
)
public class SwaggerDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SwaggerDemoApplication.class, args);
	}




}
