package dio.JWTProjectExample.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dio.JWTProjectExample.dtos.Login;
import dio.JWTProjectExample.dtos.Sessao;
import dio.JWTProjectExample.services.LoginService;

@RestController
public class LoginController {
    @Autowired
    private LoginService service;

    @PostMapping("/login")
    public Sessao logar(@RequestBody Login login){
        return service.loginAttempt(login);
    }
}