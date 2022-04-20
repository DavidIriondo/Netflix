package com.everis.d4i.tutorial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.everis.d4i.tutorial.security.services.Impl.UserServiceImpl;

@SpringBootApplication
public class NetflixMain {
	
	public static void main(String[] args) {
		SpringApplication.run(NetflixMain.class, args);
	
	}

}