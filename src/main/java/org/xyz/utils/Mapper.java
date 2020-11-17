package org.xyz.utils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.xyz.api.rest.authentication.Request.UserRequest;
import org.xyz.core.component.application.dto.UserDTO;
import org.xyz.core.component.domain.User;



public final class Mapper {
    
    public static List<UserDTO> toUsers(List<User> users){
       return users.stream()
             .map(x->toUserDTO(x))
             .collect(Collectors.toList());
    }

    public static UserDTO toUserDTO(User user) {
        if(user == null)
            return null;
            
        var dto = UserDTO.builder()
                    .id(user.getId())
                    .email(user.getEmail())
                    .name(user.getName())
                    .password(user.getPassword())
                    .lastlogin(user.getLastlogin().toString())
                    .build();

        return dto;
    }

    public static User toUser(UserDTO userDto) {
        if(userDto == null)
            return null;
            
        var user =  new User();
        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());
        user.setPassword(userDto.getPassword());
        user.setLastlogin(LocalDateTime.now());

        return user;
    }


    public static UserDTO toUserDTO(UserRequest request) {
        if(request == null)
            return null;
            
        var dto = UserDTO.builder()
                    .email(request.getEmail())
                    .name(request.getName())
                    .password(request.getPassword())
                    .build();

        return dto;
    }
}