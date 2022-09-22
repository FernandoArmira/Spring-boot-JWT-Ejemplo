package com.fernando.jwt;

import com.fernando.jwt.domain.Role;
import com.fernando.jwt.domain.User;
import com.fernando.jwt.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class JwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder(){
		return 	new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner run(UserService userService){
		return args -> {
			userService.saveRole(new Role(null,"ROLE_USER"));
			userService.saveRole(new Role(null,"ROLE_MANAGER"));
			userService.saveRole(new Role(null,"ROLE_ADMIN"));
			userService.saveRole(new Role(null,"ROLE_SUPER_ADMIN"));

			userService.saveUser(new User(null,"Fernando Armira","fer","123", new ArrayList<>()));
			userService.saveUser(new User(null,"Maria Morales","maria","123", new ArrayList<>()));
			userService.saveUser(new User(null,"Jade Perez","jade","123", new ArrayList<>()));
			userService.saveUser(new User(null,"Pedro Pablo","pp","123", new ArrayList<>()));

			userService.addRoleToUser("fer","ROLE_USER");
			userService.addRoleToUser("maria","ROLE_USER");
			userService.addRoleToUser("jade","ROLE_MANAGER");
			userService.addRoleToUser("pp","ROLE_ADMIN");
			userService.addRoleToUser("fer","ROLE_ADMIN");
			userService.addRoleToUser("fer","ROLE_SUPER_ADMIN");

		};
	}

}
