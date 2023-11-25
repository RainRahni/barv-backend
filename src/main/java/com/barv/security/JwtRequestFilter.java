package com.barv.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
@AllArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        Optional<String> jwt = getToken(request);
        if (jwt.isEmpty()) {
            filterChain.doFilter(request, response);
        }
        Claims tokenBody = parseToken(jwt.get());
    }
    private Optional<String> getToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer")) {
            return Optional.empty();
        }
        return Optional.of(header.substring("Bearer".length()));
    }
    private Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(JwtTokenProvider.key)
                .build()
                .parseClaimsJwt(token)
                .getBody();
    }
}
