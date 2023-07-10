package com.wallet.security.service.Impl;

import com.wallet.security.config.JwtService;
import com.wallet.security.model.*;
import com.wallet.security.repository.UserRepository;
import com.wallet.security.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    @Override
    public AuthenticationResponse register(AccountUserRequest request) {
        AccountUser accountUser = AccountUser.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(accountUser);
        var jwtToken = jwtService.generateToken(accountUser);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }

    @Override
    public List<AccountUserResponse> getAllAccountUser() {
        List<AccountUser> accountUsers = userRepository.findAll();
        return accountUsers.stream().map(accountUser -> mapToAccountUserRespose(accountUser)).toList();
    }

    private AccountUserResponse mapToAccountUserRespose(AccountUser accountUsers) {
        return  AccountUserResponse.builder()
                .firstname(accountUsers.getFirstname())
                .lastname(accountUsers.getLastname())
                .email(accountUsers.getEmail())
                .password(accountUsers.getPassword())
                .build();
    }
}
