package com.barv.service;

import com.barv.security.CreateUserRequest;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    void registerUser(CreateUserRequest request);

    String loginUser(CreateUserRequest request);
}
