package dio.JWTProjectExample.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EarController {

    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome to My Spring Boot Web API";
    }
    @GetMapping("/test-user-auth")
    public String users() {
        return "Authorized user";
    }
    @GetMapping("/test-managers-auth")
    public String managers() {
        return "Authorized manager";
    }
}
