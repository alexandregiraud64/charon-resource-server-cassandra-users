package com.agiraud.charon.resource.cassandra;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication(scanBasePackages={"com.agiraud.charon"})
@Configuration
public class ResourceApplication extends SpringBootServletInitializer {
	
	/* ************************************************************************* */
	// MAIN
	/* ************************************************************************* */
	public static void main(String[] args) {
		SpringApplication.run(ResourceApplication.class, args);
	}
}
