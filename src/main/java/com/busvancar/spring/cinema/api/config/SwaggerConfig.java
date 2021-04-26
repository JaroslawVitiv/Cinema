package com.busvancar.spring.cinema.api.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.hateoas.client.LinkDiscoverer;
import org.springframework.hateoas.client.LinkDiscoverers;
import org.springframework.hateoas.mediatype.collectionjson.CollectionJsonLinkDiscoverer;
import org.springframework.plugin.core.SimplePluginRegistry;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.busvancar.spring.cinema.api.UserApi;
import com.busvancar.spring.cinema.dto.UserDto;
import com.busvancar.spring.cinema.model.User;

@EnableSwagger2
@Configuration
//@EnableWebSecurity
public class SwaggerConfig{// extends WebSecurityConfigurerAdapter {
	
	//private static final String ENCODED_PASSWORD = "3297q6$087ouyasgsfipsjdfhuasert.ZJOHIg8sygd:uygGSYiuFasyi";
	/*
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	    auth.inMemoryAuthentication()
	        .passwordEncoder(passwordEncoder())
	        .withUser("user").password(ENCODED_PASSWORD).roles("USER");
	}


	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
*/
	
	@Bean
	public Docket apiV1() {
		return new Docket(DocumentationType.SWAGGER_2)
		.groupName("api-v1")
		.select()
		.apis(RequestHandlerSelectors.basePackage("com.busvancar.spring.cinema.controller"))
		.paths(PathSelectors.ant("/api/v1/**"))
		.build();
		
	}

	@Bean
	    public LinkDiscoverers discoverers() {
	        List<LinkDiscoverer> plugins = new ArrayList<>();
	        plugins.add(new CollectionJsonLinkDiscoverer());
	        return new LinkDiscoverers(SimplePluginRegistry.create(plugins));
	    }
	   
		
	
}
