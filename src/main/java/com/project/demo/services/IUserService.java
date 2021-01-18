package com.project.demo.services;

import com.project.demo.entity.User;
import com.project.demo.models.AuthenticationRequest;
import com.project.demo.models.AuthenticationResponse;

import java.util.List;

public interface IUserService {
    public User register(User user);
    public List<User> getAll();
    public AuthenticationResponse login(AuthenticationRequest authenticationRequest) throws Exception;
}
