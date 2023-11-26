package com.barv.service;

import com.barv.exception.ApplicationException;
import com.barv.mapper.UserMapper;
import com.barv.model.User;
import com.barv.repository.UserRepository;
import com.barv.security.CreateUserRequest;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
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
    private final Key jwtSecretKey = Keys.hmacShaKeyFor("Yourasuperasecretakeyathatanoaoneacanaquess".getBytes());

    @Override
    @Transactional
    public void registerUser(CreateUserRequest request) {
        User userInDatabase = userRepository.findByEmail(request.email());
        if (userInDatabase != null) {
            throw new ApplicationException("Email already registered");
        }
        User userEntity = userMapper.toEntity(request.user());
        userEntity.setEmail(request.email());
        userEntity.setPassword(passwordEncoder.encode(request.password()));
        userRepository.save(userEntity);
    }

    @Override
    @Transactional
    public String loginUser(CreateUserRequest request) {
        String encodedPassword = userRepository.findByEmail(request.email()).getPassword();
        boolean matches = passwordEncoder.matches(request.password(), encodedPassword);
        if (!matches) {
            throw new ApplicationException("Wrong email or password");
        }
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
