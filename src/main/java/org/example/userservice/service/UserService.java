package org.example.userservice.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.userservice.dto.UserDTO;
import org.example.userservice.entity.User;
import org.example.userservice.mapper.UserMapper;
import org.example.userservice.repository.UserRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
@Slf4j
public class UserService {

    private final UserMapper userMapper;
    UserRepository userRepository;

    public User createUser(UserDTO userDTO) {
        return userRepository.save(userMapper.dtoEntity().apply(userDTO));
    }
}
