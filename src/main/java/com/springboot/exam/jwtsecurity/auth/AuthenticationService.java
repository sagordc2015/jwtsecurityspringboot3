package com.springboot.exam.jwtsecurity.auth;

import com.springboot.exam.jwtsecurity.config.JwtService;
import com.springboot.exam.jwtsecurity.model.Role;
import com.springboot.exam.jwtsecurity.model.Users;
import com.springboot.exam.jwtsecurity.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var users = Users.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        usersRepository.save(users);
        var jwtToken = jwtService.generateToken(users);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        var users = usersRepository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(users);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}
