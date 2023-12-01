package com.wazaaa.joq.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.wazaaa.joq.services.CustomUserDetailsService;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private final CustomUserDetailsService userDetailsService;
	
  
	
	public SecurityConfig(CustomUserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}
	
	@Bean
	public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder) throws Exception {		
		AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
		authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
		return authenticationManagerBuilder.build();
	}
	
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests()

                .requestMatchers("administrador").hasRole("ADMINISTRADOR")
                .requestMatchers("administrador/**").hasRole("ADMINISTRADOR")
                .requestMatchers("usuario").hasRole("USUARIO")
                .requestMatchers("usuario/**").hasRole("USUARIO")
                .requestMatchers("login").permitAll()
                .requestMatchers("register").permitAll()
                .anyRequest().authenticated();
        http
                .formLogin()
	                .loginPage("/login")
	                .successHandler((request, response, authentication) -> {
	                    for (GrantedAuthority auth : authentication.getAuthorities()) {
	                        if (auth.getAuthority().equals("ROLE_USUARIO")) {
	                            response.sendRedirect("/usuario");
	                        } else if (auth.getAuthority().equals("ROLE_ADMINISTRADOR")) {
	                            response.sendRedirect("/administrador");
	                        }
	                    }
	                })
			        .and()
			        .exceptionHandling()
			            .accessDeniedHandler((request, response, accessDeniedException) -> {
			                Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			                if (auth != null) {
			                    for (GrantedAuthority authority : auth.getAuthorities()) {
			                        if (authority.getAuthority().equals("ROLE_USUARIO")) {
			                            response.sendRedirect("/usuario");
			                            return;
			                        } else if (authority.getAuthority().equals("ROLE_ADMINISTRADOR")) {
			                            response.sendRedirect("/administrador");
			                            return;
			                        }
			                    }
			                }
			                response.sendRedirect("/login");
			            });


        return http.build();
    }


    
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
    	return new BCryptPasswordEncoder();
    }

}