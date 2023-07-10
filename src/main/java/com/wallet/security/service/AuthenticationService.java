package com.wallet.security.service;

import com.wallet.security.model.AccountUserRequest;
import com.wallet.security.model.AccountUserResponse;
import com.wallet.security.model.AuthenticationRequest;
import com.wallet.security.model.AuthenticationResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface AuthenticationService {
    public AuthenticationResponse register(AccountUserRequest request);

    public AuthenticationResponse authenticate(AuthenticationRequest request) ;

    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException;
    List<AccountUserResponse> getAllAccountUser();
}
