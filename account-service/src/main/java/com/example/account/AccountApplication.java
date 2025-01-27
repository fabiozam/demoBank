package com.example.account;

import com.example.account.Database.DBConnection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.sql.SQLException;

@SpringBootApplication
public class AccountApplication {

	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(AccountApplication.class);

	public static void main(String[] args) {
		DBConnection db = new DBConnection();
		log.info("Connecting to the database...");
		try {
			db.testConnection();
			log.info("Connection to the database succeed!");
		} catch (SQLException e) {
			log.error("Failed to connect to database. {}", e.getMessage());
			throw new RuntimeException(e);
		};
		SpringApplication.run(AccountApplication.class, args);
	}

	@EnableWebSecurity
	@Configuration
	class WebSecurityConfig extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.csrf().disable()
					.addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
					.authorizeRequests()
					.antMatchers(HttpMethod.POST, "/user").permitAll()
					.antMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
					.anyRequest().authenticated();
		}
	}

}
