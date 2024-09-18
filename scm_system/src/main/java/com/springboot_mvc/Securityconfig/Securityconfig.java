package com.springboot_mvc.Securityconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.springboot_mvc.CustomeUserDetails.Customeuserdetails;
import com.springboot_mvc.CustomeUserDetailsService.Customeuserdetailsservice;

@Configuration
@EnableWebSecurity

public class Securityconfig {
	@Autowired
	Customeuserdetailsservice customeuserdetailsservice;
	
	
	@Bean
	public static PasswordEncoder passwordencoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http
	        .authorizeHttpRequests(authorize -> authorize
	            .requestMatchers("/register", "/login", "/images/**").permitAll()
	            .requestMatchers(HttpMethod.POST, "/delete/{id}").hasRole("ADMIN") // Restrict delete to POST requests
	            .requestMatchers("/user/**").permitAll()
	            .anyRequest().authenticated()
	          
	        )
	        .formLogin(form -> form
	            .loginPage("/login")
	            .loginProcessingUrl("/login")
	          
	            .defaultSuccessUrl("/home", true)
	            .permitAll()
	        )
	        .logout(logout -> logout
	            .invalidateHttpSession(true)
	            .clearAuthentication(true)
	            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
	            .logoutSuccessUrl("/login?logout")
	            .permitAll()
	        );

	    return http.build();
	}
	@Autowired
	public void configureGloble(AuthenticationManagerBuilder auth) throws Exception{
		
		auth.userDetailsService(customeuserdetailsservice).passwordEncoder(passwordencoder());
	}
}
