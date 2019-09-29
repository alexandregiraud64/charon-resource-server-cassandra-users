package com.agiraud.charon.resource.cassandra.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import com.agiraud.charon.resource.config.ResourceServer;

@Configuration
@EnableResourceServer
public class CassandraResourceServer extends ResourceServer {

	@Override
	public void configure(HttpSecurity http) throws Exception {

		http
			.authorizeRequests()
			.antMatchers("/me", "email", "phone")
			.authenticated();
	}
	
}