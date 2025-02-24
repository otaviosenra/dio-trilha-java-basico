package dio.JWTProjectExample.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {
    
    
    private static final String[] SWAGGER_WHITELIST = {
            "/v2/api-docs",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/docs#",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui/**",
            "/webjars/**"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .headers(headers -> headers.frameOptions().disable()) // Permite H2-Console
            .cors(cors -> cors.disable()) // Desabilita CORS (ajuste conforme necessário)
            .csrf(csrf -> csrf.disable()) // Desabilita CSRF para API stateless
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // JWT não usa sessão
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(SWAGGER_WHITELIST).permitAll() // Libera Swagger
                .requestMatchers(HttpMethod.POST, "/login").permitAll() // Libera login
                .requestMatchers(HttpMethod.POST, "/users").permitAll() // Cadastro de usuário público
                .requestMatchers(HttpMethod.GET, "/users").hasAnyRole("USERS", "MANAGERS") // Protege endpoint users
                .requestMatchers("/managers").hasRole("MANAGERS") // Protege managers
                .anyRequest().authenticated() // Qualquer outra requisição precisa estar autenticada
            )
            .addFilterAfter(new JWTFilter(), UsernamePasswordAuthenticationFilter.class); // Adiciona o filtro JWT

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}