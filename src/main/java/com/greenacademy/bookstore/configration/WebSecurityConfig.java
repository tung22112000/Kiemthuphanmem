package com.greenacademy.bookstore.configration;

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

import com.greenacademy.bookstore.service.impl.UserDetailsServiceImpl;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true, proxyTargetClass = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetailsServiceImpl();
	};

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	};

//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//
//		auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
//	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/", "/shop/**", "/home/**", "/error", "/uploads/**", "/resources/**",
				"/error", "/login", "/forgotPassword", "/changePassword", "/resetPassword").permitAll()
//				.antMatchers("/views/student/**").access("hasRole('USER')")
				.antMatchers("/admin/**").hasRole("AUTH").anyRequest().authenticated().and().formLogin()
				.loginPage("/login").defaultSuccessUrl("/admin/customer/", true)
//				.defaultSuccessUrl("/admin/timeSheets/")
				.failureUrl("/login?error").permitAll().and().logout().permitAll().logoutSuccessUrl("/login").and()
				.csrf().disable();
	}

}
