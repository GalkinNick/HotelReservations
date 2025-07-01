package com.example.HotelReservations.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(12);
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.
                csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                                .requestMatchers("/register").permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/hotels").permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/rooms/").permitAll()
                                .requestMatchers("/api/hotels/").hasRole("ADMIN")
                                .requestMatchers("/api/rooms/**").hasRole("ADMIN")
                                .requestMatchers("/api/booking").hasRole("ADMIN")
                                .anyRequest().authenticated()
                );
        

        return http.build();
    }





}
