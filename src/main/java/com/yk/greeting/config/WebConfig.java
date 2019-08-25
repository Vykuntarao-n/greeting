package com.yk.greeting.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/**
 * 
 * @author Vykuntarao
 *
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
	/**
	 * Configuration for all the Web Context  
	 * Redirect the Context path to swagger UI URL
	 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addRedirectViewController("/", "/swagger-ui.html");
	}
}