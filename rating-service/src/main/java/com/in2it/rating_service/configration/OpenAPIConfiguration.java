package com.in2it.rating_service.configration;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenAPIConfiguration {
	
	
		@Bean
	   public OpenAPI defineOpenApi() {
	       Server server = new Server();
	       server.setUrl("http://localhost:8083");
	       server.setDescription("Microservices Development");

	       Contact myContact = new Contact();
	       myContact.setName("Kishan Kashyap");
	       myContact.setEmail("kishan.kashyap@in2ittech.com");

	       Info information = new Info()
	               .title(" Rating service API (Microservices)")
	               .version("1.0")
	               .description("This API exposes endpoints to manage rating.")
	               .contact(myContact);
	       return new OpenAPI().info(information).servers(List.of(server));
	   }

}
