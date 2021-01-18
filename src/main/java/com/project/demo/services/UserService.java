package com.project.demo.services;

import com.project.demo.dao.UserRepository;
import com.project.demo.entity.User;
import com.project.demo.models.AuthenticationRequest;
import com.project.demo.models.AuthenticationResponse;
import com.project.demo.models.JwtUtil;
import com.project.demo.models.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    MyUserDetailsService userDetailsService;

    @Autowired
    JwtUtil jwtUtil;

    @Override
    public User register(User user) {
        user.setActive(true);
        userRepo.save(user);
        return user;
    }

    @Override
    public List<User> getAll() {
        return userRepo.findAll();
    }

    @Override
    public AuthenticationResponse login(AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));
        }
        catch(BadCredentialsException e) {
            throw new Exception("Incorrect email or password");
        }

        final UserDetails userDetails=userDetailsService
                .loadUserByUsername(authenticationRequest.getEmail());
        final String jwt=jwtUtil.generateToken(userDetails);

        return new AuthenticationResponse(jwt);
    }
}
