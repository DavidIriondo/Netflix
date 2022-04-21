package com.everis.d4i.tutorial.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
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

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	    auth.userDetailsService(userDetailsService());

	}
	@Bean
	@Override
	public UserDetailsService userDetailsService() {
	    UserDetails user =
	            User.withDefaultPasswordEncoder()
	                    .username("user")
	                    .password("user")
	                    .roles("USER")
	                    .build();

	    return new InMemoryUserDetailsManager(user);
	}

	@Override
    public void configure(HttpSecurity http) throws Exception {
       http
       .authorizeRequests()
       .antMatchers("/**").hasRole("ADMIN")
       .and()
       .formLogin().permitAll();
       
       
       /*
        http
       .httpBasic()
       .and()
       .authorizeRequests()
		.antMatchers(RestConstants.RESOURCE_TV_SHOW + "/**").hasAnyAuthority("ADMIN")
	    .antMatchers(RestConstants.RESOURCE_ACTOR + "/**").hasAnyAuthority("USER")
       .and()
       .formLogin().permitAll()
       .defaultSuccessUrl("/swagger-ui.html", true)
       .and()
       .logout().permitAll();
        * */

    }
	
	
}
