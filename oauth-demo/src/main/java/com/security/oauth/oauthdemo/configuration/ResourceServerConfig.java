package com.security.oauth.oauthdemo.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfiguration;

@EnableResourceServer
@Configuration
@Order(1000) 
public class ResourceServerConfig extends ResourceServerConfiguration{
	@Override
	public void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.authorizeRequests().antMatchers("/").permitAll().
		antMatchers("/oauth/**").authenticated();
	}
}
