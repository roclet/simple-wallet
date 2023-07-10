package com.wallet.security.controller;

import com.wallet.security.model.AccountUserRequest;
import com.wallet.security.model.AccountUserResponse;
import com.wallet.security.model.AuthenticationRequest;
import com.wallet.security.model.AuthenticationResponse;
import com.wallet.security.service.AccountUserService;
import com.wallet.security.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/wallet/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody AccountUserRequest request
    ) {
        return ResponseEntity.ok(service.register(request));
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }

    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        service.refreshToken(request, response);
    }

    @GetMapping({"/all/user"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<AccountUserResponse>> getAllAccountUser(){
        return ResponseEntity.ok(service.getAllAccountUser());
    }
}
