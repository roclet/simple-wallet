package com.wallet.security.config;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

// To filter all request
@Component
@RequiredArgsConstructor // this create a construct with  all declared final string
public class JwtAuthentificationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
      // Request , response, we can intercept
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
         // to get jwt token
         final String authHeader = request.getHeader("Authorization");
         final String jwt ;
         final String userEmail;
         if (authHeader == null || ! authHeader.startsWith("Bearer ")){
             // pass it to the next filter , don't continue
             filterChain.doFilter(request, response);
             return;
         }
        jwt = authHeader.substring(7);
         // to extract the userEmail from JWT token;
        userEmail = jwtService.extractUsername(jwt);
        // to check if user is already Authentication
        if(userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null){
            // Is not Authentication
            // get or check user details
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
//            var isTokenValid = tokenRepository.findByToken(jwt)
//                    .map(t -> !t.isExpired() && !t.isRevoked())
//                    .orElse(false);
            if (jwtService.isTokenValid(jwt, userDetails) ) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                // To Update the security context
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
