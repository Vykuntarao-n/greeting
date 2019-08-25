package com.yk.greeting.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
/**
 * 
 * @author Vykuntarao
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

	private static final String CONTROLLERS_BASE_PACKAGE = "com.yk.greeting.controller";
	/**
	 * Swagger Configuration to generate the Swagger Documentation
	 * @return
	 */
	@Bean
	public Docket apiDocket() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage(CONTROLLERS_BASE_PACKAGE))
				.paths(PathSelectors.any()).build();
	}

}
