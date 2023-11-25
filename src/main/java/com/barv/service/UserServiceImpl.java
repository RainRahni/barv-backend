package com.barv.service;

import com.barv.mapper.UserMapper;
import com.barv.model.User;
import com.barv.repository.UserRepository;
import com.barv.security.CreateUserRequest;
import lombok.RequiredArgsConstructor;
import org.checkerframework.common.util.report.qual.ReportWrite;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public void registerUser(CreateUserRequest request) {
        User user = userMapper.toUserEntity(request.user());
        user.setEmail(request.email()); 
        user.setPassword(passwordEncoder.encode(request.password()));
        userRepository.save(user);
    }
}
