package dio.rest_api.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .csrf().disable() // Desabilita CSRF (cuidado em produção)
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll() // Libera o acesso ao Swagger
                .requestMatchers("/api/public").permitAll() // Libera o acesso ao endpoint público (se houver)
                .anyRequest().authenticated() // Exige autenticação para qualquer outra requisição
            )
            .httpBasic() // Habilita autenticação básica
            .and()
            .build();
    }
}
