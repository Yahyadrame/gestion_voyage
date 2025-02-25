package com.uasz.gestion_voyages.Authentification.security;

import com.uasz.gestion_voyages.Authentification.service.UtilisateurDetailsService;
import com.uasz.gestion_voyages.Authentification.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final String[] FOR_ENSEIGNANT = {"/api/enseignant/**"};
    private static final String[] FOR_DRC = {"/api/drc/**"};
    private static final String[] FOR_DRH = {"/api/drh/**"};
    private static final String[] FOR_DFC = {"/api/dfc/**"};

    @Autowired
    private UtilisateurDetailsService utilisateurDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/login").permitAll()
                        .requestMatchers(FOR_ENSEIGNANT).hasRole("ENSEIGNANT")
                        .requestMatchers(FOR_DRC).hasRole("DRC")
                        .requestMatchers(FOR_DRH).hasRole("DRH")
                        .requestMatchers(FOR_DFC).hasRole("DFC")
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}
