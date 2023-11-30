package com.barv.controller;

import com.barv.dto.UserDTO;
import com.barv.service.UserServiceImpl;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Role;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public void registerUser(@RequestBody UserDTO user) {
        userService.registerUser(user);
    }
    @Transactional
    @PostMapping("public/login")
    public String loginUser(@RequestBody UserDTO user) {
        String s = userService.loginUser(user);
        System.out.println(s);
        return s;
    }
    @GetMapping("test")
    public String test() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String principalName = authentication.getName();
        return "Hello, " + principalName + "!";
    }
}
