package org.example.userservice.mapper;

import org.example.userservice.dto.UserDTO;
import org.example.userservice.entity.User;
import org.example.userservice.repository.MappingEntityToDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class UserMapper implements MappingEntityToDTO <UserDTO, User>{
    @Override
    public Function <UserDTO, User> dtoEntity() {
        return dto -> {
            User user = new User();
            BeanUtils.copyProperties(dto, user);
            return user;
        };
    }
}
