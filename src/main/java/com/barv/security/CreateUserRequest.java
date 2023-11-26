package com.barv.security;

import com.barv.dto.UserDTO;

public record CreateUserRequest (UserDTO user, String email, String password) {
}
