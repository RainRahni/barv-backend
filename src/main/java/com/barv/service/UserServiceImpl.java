package com.barv.service;

import com.barv.dto.UserDTO;
import com.barv.exception.ApplicationException;
import com.barv.mapper.UserMapper;
import com.barv.model.User;
import com.barv.repository.UserRepository;
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
    public void registerUser(UserDTO user) {
        User userInDatabase = userRepository.findByEmail(user.email());
        if (userInDatabase != null) {
            throw new ApplicationException("Email already registered");
        }
        User userEntity = userMapper.toUserEntity(user);
        userEntity.setPassword(passwordEncoder.encode(user.password()));
        System.out.println();
        userRepository.save(userEntity);
    }
    @Override
    @Transactional
    public String loginUser(UserDTO user) {
        String encodedPassword = userRepository.findByEmail(user.email()).getPassword();
        boolean matches = passwordEncoder.matches(user.password(), encodedPassword);
        if (!matches) {
            throw new ApplicationException("Wrong email or password");
        }
        Map<String, Object> claims = new HashMap<>();
        claims.put("email", user.email());
        return Jwts.builder()
                .subject("towho")
                .claims(claims)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + (30 * 60 * 1000)))
                .signWith(jwtSecretKey, SignatureAlgorithm.HS256)
                .compact();
    }

}
