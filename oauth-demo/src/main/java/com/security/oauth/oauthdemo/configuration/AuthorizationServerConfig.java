package com.security.oauth.oauthdemo.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

@EnableAuthorizationServer
@Configuration
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

//	private UserDetailsService userDetailsService;
//	
//	@Autowired
//	public void authenticationManager(AuthenticationManagerBuilder builder) throws Exception {
//	    builder.userDetailsService(userDetailsService);
//	}
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security
        .tokenKeyAccess("permitAll()")
        .checkTokenAccess("isAuthenticated()")
        .allowFormAuthenticationForClients();
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory().withClient("client")
        .authorizedGrantTypes("client-credentials", "password","refresh_token")
        .authorities("ROLE_CLIENT", "ROLE_ANDROID_CLIENT")
        .scopes("read", "write", "trust")
        .resourceIds("oauth2-resource")
        .accessTokenValiditySeconds(5000)
        .secret("android-secret").refreshTokenValiditySeconds(50000);
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
	    endpoints.authenticationManager(authenticationManager)
	            .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
	            .tokenEnhancer(new CustomTokenEnhancer());
	}
}
