package com.barv.service;

import com.barv.mapper.UserMapper;
import com.barv.model.User;
import com.barv.repository.UserRepository;
import com.barv.security.CreateUserRequest;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.checkerframework.common.util.report.qual.ReportWrite;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    //public and private key better for sign in,  not password.
    @Override
    public void registerUser(CreateUserRequest request) {
        //TODO check if email not in database
        User user = userMapper.toUserEntity(request.user());
        user.setEmail(request.email()); 
        user.setPassword(passwordEncoder.encode(request.password()));
        userRepository.save(user);
    }

    @Override
    public String loginUser(CreateUserRequest request) {
        //TODO: throwi v anna mingi tagaside errori puhul, invalid.
        String encodedPassword = userRepository.findByEmail(request.email()).getPassword();
        boolean matches = passwordEncoder.matches(request.password(), encodedPassword);
        Map<String, Object> claims = new HashMap<>();
        claims.put("email", request.email());
        return Jwts.builder()
                .subject("towho")
                .claims(claims)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + (30 * 60 * 1000)))
                .signWith(jwtSecretKey, SignatureAlgorithm.HS256)
                .compact();
    }

}
