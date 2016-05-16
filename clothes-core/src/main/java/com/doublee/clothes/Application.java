package com.doublee.clothes;

import static springfox.documentation.builders.PathSelectors.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.common.base.Predicate;

import static com.google.common.base.Predicates.*;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@ComponentScan(
  {"com.doublee.clothes.controllers", 
   "com.doublee.clothes.config",
   "com.doublee.clothes.service"}
)
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean 
	public Docket clothesApi() {
		return new Docket(DocumentationType.SWAGGER_2)
			.apiInfo(apiInfo())
			.select()
			.apis(RequestHandlerSelectors.any())
			.paths(paths())			
			.build()
			.useDefaultResponseMessages(false)
			.globalResponseMessage(RequestMethod.GET, buildMessages());
	}
	private Predicate<String> paths() {
	    return or(
	        regex("/clothes.*"),
	        regex("/other.*")
	    );
	}
	private List<ResponseMessage> buildMessages() {
		List<ResponseMessage> messages = new ArrayList<ResponseMessage>();
		ResponseMessage message500 = new ResponseMessageBuilder()
	      .code(500)
	      .message("Internal Server Error.")
	      .responseModel(new ModelRef("Error"))
	      .build();
		ResponseMessage message403 = new ResponseMessageBuilder()
	      .code(403)
	      .message("403 Forbidden.")
	      .responseModel(new ModelRef("Error"))
	      .build();
		ResponseMessage message404 = new ResponseMessageBuilder()
	      .code(404)
	      .message("404 Clothe Not Found.")
	      .responseModel(new ModelRef("Error"))
	      .build();
		ResponseMessage message405 = new ResponseMessageBuilder()
	      .code(405)
	      .message("405 Method not allowed.")
	      .responseModel(new ModelRef("Error"))
	      .build();
		messages.add(message500);
		messages.add(message403);
		messages.add(message404);
		messages.add(message405);
		return messages;
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
			.title("Clothes Microservice API")
			.description("Main documentation of Endpoints.")
			.contact("Erikson Murrugarra")
			.license("Apache License Version 2.0")
			.licenseUrl("somewhere.com")
			.version("2.0")
			.build();
			
	}

}
