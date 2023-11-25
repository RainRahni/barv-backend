package com.barv.controller;

import com.barv.security.CreateUserRequest;
import com.barv.service.UserServiceImpl;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
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
    @PostMapping("public/register")
    public void registerUser(@RequestBody CreateUserRequest request) {
        userService.registerUser(request);
    }
    @Transactional
    @PostMapping("public/login")
    public String loginUser(@RequestBody CreateUserRequest request) {
        return userService.loginUser(request);
    }
    @GetMapping("test")
    public String test() {
        return "test";
    }
}
