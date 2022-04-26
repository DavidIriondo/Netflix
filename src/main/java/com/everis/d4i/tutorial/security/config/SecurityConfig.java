package com.everis.d4i.tutorial.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.everis.d4i.tutorial.security.services.Impl.UserServiceImpl;
import com.everis.d4i.tutorial.utils.constants.RestConstants;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@Autowired
	@Lazy
	private BCryptPasswordEncoder bCrypt;
	
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	} 
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {return NoOpPasswordEncoder.getInstance();}

	//AUTHENTICATION
	/*
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	   auth.inMemoryAuthentication()
	   .withUser("user")
	   .password("{noop}user")
	   .roles("USER")
	   .and()
	   .withUser("admin")
	   .password("{noop}admin")
	   .roles("ADMIN");
	}*/
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	   auth.userDetailsService(userServiceImpl).passwordEncoder(bCrypt);
	}
	
	
	//AUTHORIZATION
	@Override
    public void configure(HttpSecurity http) throws Exception {
		
		http
	   .csrf().disable()
       .authorizeRequests()
       //ADD CATEGORIES TO A SINGLE SHOW TV
       .antMatchers(HttpMethod.POST,RestConstants.APPLICATION_NAME + RestConstants.API_VERSION_1 + RestConstants.RESOURCE_TV_SHOW +
    		   RestConstants.RESOURCE_ID + RestConstants.RESOURCE_CATEGORY).hasRole("ADMIN")
       //MODIFY TV SHOW´S NAME
       .antMatchers(HttpMethod.PATCH,RestConstants.APPLICATION_NAME + RestConstants.API_VERSION_1 + RestConstants.RESOURCE_TV_SHOW +
    		   RestConstants.RESOURCE_ID).hasRole("ADMIN")
       //MODIFY CHAPTER´S NAME
       .antMatchers(HttpMethod.PATCH,RestConstants.APPLICATION_NAME + RestConstants.API_VERSION_1 +RestConstants.RESOURCE_CHAPTER +
    		   RestConstants.RESOURCE_ID).hasRole("ADMIN")
       //DELETE TV SHOW
       .antMatchers(HttpMethod.DELETE,RestConstants.APPLICATION_NAME + RestConstants.API_VERSION_1 + RestConstants.RESOURCE_TV_SHOW +
    		   RestConstants.RESOURCE_ID).hasRole("ADMIN")
       //LIST OF ACTORS
       .antMatchers(HttpMethod.GET, RestConstants.APPLICATION_NAME + RestConstants.API_VERSION_1 +
    		   RestConstants.RESOURCE_ACTOR).hasRole("USER")
       //GET SINGLE ACTOR
       .antMatchers(HttpMethod.GET, RestConstants.APPLICATION_NAME + RestConstants.API_VERSION_1 +
    		   RestConstants.RESOURCE_ACTOR + RestConstants.RESOURCE_ID).hasRole("USER")
       //CREATE NEW ACTOR
       .antMatchers(HttpMethod.POST, RestConstants.APPLICATION_NAME + RestConstants.API_VERSION_1 +
    		   RestConstants.RESOURCE_ACTOR).hasRole("ADMIN")
       //MODIFY SINGLE ACTOR
       .antMatchers(HttpMethod.PATCH, RestConstants.APPLICATION_NAME + RestConstants.API_VERSION_1 +
    		   RestConstants.RESOURCE_ACTOR + RestConstants.RESOURCE_ID).hasRole("ADMIN")
       //DELETE SINGLE ACTOR
       .antMatchers(HttpMethod.DELETE, RestConstants.APPLICATION_NAME + RestConstants.API_VERSION_1 +
    		   RestConstants.RESOURCE_ACTOR + RestConstants.RESOURCE_ID).hasRole("ADMIN")
       //SINGLE ACTOR TVSHOWS
       .antMatchers(HttpMethod.GET, RestConstants.APPLICATION_NAME + RestConstants.API_VERSION_1 +
    		   RestConstants.RESOURCE_ACTOR + RestConstants.RESOURCE_ID + RestConstants.RESOURCE_TV_SHOW).hasRole("USER")
       //TV SHOW´S AWARDS
       .antMatchers(HttpMethod.GET, RestConstants.APPLICATION_NAME + RestConstants.API_VERSION_1 +
    		   RestConstants.RESOURCE_TV_SHOW + RestConstants.RESOURCE_ID + RestConstants.RESOURCE_AWARD).hasRole("USER")
       .anyRequest()
       .authenticated()
       .and()
       .httpBasic();

    }
	
	
}
