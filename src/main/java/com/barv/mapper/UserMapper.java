package com.barv.mapper;

import com.barv.dto.UserDTO;
import com.barv.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    UserDTO toDto(User user);
    User toEntity(UserDTO userDTO);
}
