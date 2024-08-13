package com.example.jwtdemo.auth;

import com.example.jwtdemo.config.JwtService;
import com.example.jwtdemo.user.Role;
import com.example.jwtdemo.user.User;
import com.example.jwtdemo.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEcoder;
    private final JwtService jwtService;

    public AuthenticationResponse register(RegisterRequest request){
        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEcoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    };

    public AuthenticationResponse authenticate(AuthenticationRequest request){
        return null;
    };
}
