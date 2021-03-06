package com.exist.ecc.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	private UserDetailsService userDetailsService;

	@Autowired
	public SpringSecurityConfig(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("Mik").password("123123").roles("ADMIN");
		auth.userDetailsService(this.userDetailsService).passwordEncoder( passwordEncoder() );
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.authorizeRequests()
			.antMatchers("/login*", "/resources/**").permitAll()
			.anyRequest().authenticated()
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
				.logoutSuccessUrl("/login")
			.and()
				.exceptionHandling()
				.accessDeniedPage("/403");

	}
}
