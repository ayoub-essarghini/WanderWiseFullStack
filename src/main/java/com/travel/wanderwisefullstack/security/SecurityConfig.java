package com.travel.wanderwisefullstack.security;


import com.travel.wanderwisefullstack.Services.AccountService;
import com.travel.wanderwisefullstack.filters.JwtAuthFilter;
import com.travel.wanderwisefullstack.filters.JwtFilter;
import com.travel.wanderwisefullstack.models.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.ArrayList;
import java.util.Collection;


@Configuration
@EnableWebSecurity
public class SecurityConfig {


    private final AccountService accountService;

    public SecurityConfig(AccountService accountService) {
        this.accountService = accountService;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            AppUser appUser = accountService.loadUserByUsername(username);
            if (appUser == null) {
                throw new UsernameNotFoundException("User not found: " + username);
            }
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            appUser.getRoles().forEach(r -> {
                authorities.add(new SimpleGrantedAuthority(r.getName()));
            });
            return new User(appUser.getUsername(), appUser.getPassword(), authorities);
        };
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationManager authenticationManager) throws Exception {

        http.csrf(AbstractHttpConfigurer::disable).sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).headers(headers -> headers
                // Disable all default headers
                .frameOptions(HeadersConfigurer.FrameOptionsConfig::disable) // Disable only X-Frame-Options
        ).authorizeHttpRequests(authorize -> authorize.requestMatchers("/h2-console/**").permitAll().anyRequest().authenticated()).addFilter(new JwtFilter(authenticationManager)).addFilterBefore(new JwtAuthFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService());
        return authenticationManagerBuilder.build();
    }


}