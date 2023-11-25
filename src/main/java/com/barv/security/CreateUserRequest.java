package com.barv.security;

import com.barv.dto.UserDTO;
import lombok.Data;
import lombok.Getter;

public record CreateUserRequest (UserDTO user, String email, String password) {
}
