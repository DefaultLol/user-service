package com.project.demo.web;

import com.project.demo.entity.User;
import com.project.demo.models.AuthenticationRequest;
import com.project.demo.models.AuthenticationResponse;
import com.project.demo.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private IUserService service;

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        return service.login(authenticationRequest);
    }

    @GetMapping("/getAll")
    public List<User> getAll(){
        return service.getAll();
    }

    @PostMapping("/register")
    public User register(@RequestBody User user){
        service.register(user);
        return user;
    }
}
