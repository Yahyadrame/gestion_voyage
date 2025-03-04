package com.uasz.gestion_voyages.Authentification.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final String[] FOR_ENSEIGNANT = {"/api/enseignant/**"};
    private static final String[] FOR_DRC = {"/api/drc/**"};
    private static final String[] FOR_DRH = {"/api/drh/**"};
    private static final String[] FOR_DFC = {"/api/dfc/**"};

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(corsConfigurationSource())) // Activer CORS
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll() // Autoriser les requêtes OPTIONS
                        .requestMatchers("/api/auth/login").permitAll()
                        .requestMatchers("/api/drc/**").hasAuthority("DRC")
                        .requestMatchers(FOR_ENSEIGNANT).hasAuthority("ENSEIGNANT")
                        .requestMatchers(FOR_DRH).hasAuthority("DRH")
                        .requestMatchers(FOR_DFC).hasAuthority("DFC")
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public UrlBasedCorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true); // Si vous utilisez des cookies ou des en-têtes d'authentification
        config.addAllowedOrigin("http://localhost:4200"); // Autoriser votre frontend Angular
        config.addAllowedHeader("*"); // Autoriser tous les en-têtes
        config.addAllowedMethod("*"); // Autoriser toutes les méthodes (GET, POST, etc.)
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}