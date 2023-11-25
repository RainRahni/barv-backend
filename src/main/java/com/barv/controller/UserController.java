package com.barv.controller;

import com.barv.security.CreateUserRequest;
import com.barv.service.UserServiceImpl;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {
    private final UserServiceImpl userService;
    @Transactional
    @PostMapping("/register")
    public void registerUser(@RequestBody CreateUserRequest request) {
        userService.registerUser(request);
    }
}
