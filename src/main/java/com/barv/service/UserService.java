package com.barv.service;

import com.barv.dto.UserDTO;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    void registerUser(UserDTO userDTO);

    String loginUser(UserDTO userDTO);
}
