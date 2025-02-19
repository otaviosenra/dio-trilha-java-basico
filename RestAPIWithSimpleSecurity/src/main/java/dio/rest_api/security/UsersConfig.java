package dio.rest_api.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


// ESTAS CONFIGURAÇÕES SERVEM PARA DEFINIR UM USUARIO E SENHA EM MEMÓRIA PARA ACESSAR A API

@Configuration
public class UsersConfig {
    
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails admin = User.withUsername("admin")
                .password("{noop}admin123") // {noop} indica senha sem criptografia (apenas para testes)
                .roles("ADMIN")
                .build();

        UserDetails user = User.withUsername("user")
                .password("{noop}user123")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(admin, user);
    }
}
