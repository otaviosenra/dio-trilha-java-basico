package dio.JWTProjectExample.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dio.JWTProjectExample.models.Users;
import dio.JWTProjectExample.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired UserService service;

    @PostMapping
    public void postUser(@RequestBody Users user){
        service.createUser(user);
    }

}
