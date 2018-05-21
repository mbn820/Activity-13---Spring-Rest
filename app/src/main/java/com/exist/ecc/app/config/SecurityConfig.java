package com.exist.ecc.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("Mik")
			.password("123456")
			.roles("ADMIN");
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/home*").hasRole("ADMIN")
			.antMatchers("/*/manage*").hasRole("ADMIN")
			.and()
			    .formLogin()
					.loginPage("/login")
					.loginProcessingUrl("/signin")
					.failureUrl("/login?error")
					.defaultSuccessUrl("/home")
			    .usernameParameter("username").passwordParameter("password")
			.and()
			    .logout()
				.logoutUrl("/signout")
				.logoutSuccessUrl("/login");
	}
}
