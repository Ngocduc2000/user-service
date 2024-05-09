package org.example.userservice.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.userservice.dto.AuthenticationDTO;
import org.example.userservice.exception.AppException;
import org.example.userservice.exception.ErrorCode;
import org.example.userservice.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticateService {
//    UserRepository userRepository;
//
//    public boolean authenticate(AuthenticationDTO authenRequest){
//        var user = userRepository.findByUsername(authenRequest.getUsername())
//                .orElseThrow(()-> new AppException(ErrorCode.USER_NOT_EXISTED));
//        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
//        return passwordEncoder.matches(authenRequest.getPassword(), user.getPassword());
//    }
}
