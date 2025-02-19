package dio.JWTProjectExample.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import dio.JWTProjectExample.dtos.Login;
import dio.JWTProjectExample.dtos.Sessao;
import dio.JWTProjectExample.models.Users;
import dio.JWTProjectExample.repositories.UserRepository;
import dio.JWTProjectExample.security.JWTCreator;
import dio.JWTProjectExample.security.JWTObject;
import dio.JWTProjectExample.security.SecurityConfig;

@Service
public class LoginService {

    @Autowired
    private PasswordEncoder encoder;
    
    @Autowired
    private UserRepository repository;

    public Sessao loginAttempt(Login login){
        Users user = repository.findByUsername(login.getUsername());
        if(user!=null) {
            boolean passwordOk =  encoder.matches(login.getPassword(), user.getPassword());
            if (!passwordOk) {
                throw new RuntimeException("Senha inválida para o login: " + login.getUsername());
            }

            //Enviando um objeto Sessão para retornar as informações pro usuário
            Sessao sessao = new Sessao();
            sessao.setLogin(user.getUsername());

            JWTObject jwtObject = new JWTObject();
            jwtObject.setIssuedAt(new Date(System.currentTimeMillis()));
            jwtObject.setExpiration((new Date(System.currentTimeMillis() + SecurityConfig.EXPIRATION)));
            jwtObject.setRoles(user.getRoles());
            sessao.setToken(JWTCreator.create(SecurityConfig.PREFIX, SecurityConfig.KEY, jwtObject));
            return sessao;
        }else {
            throw new RuntimeException("Erro ao tentar fazer login");
        }
    }
}
