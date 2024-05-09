package org.example.userservice.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.userservice.dto.ApiResponse;
import org.example.userservice.dto.AuthenticationDTO;
import org.example.userservice.dto.AuthenticationResponseDTO;
import org.example.userservice.service.AuthenticateService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
//    AuthenticateService authenticationService;
//
//    @PostMapping("/log-in")
//    ApiResponse<AuthenticationResponseDTO> authenticate(@RequestBody AuthenticationDTO authenRequest){
//        boolean result = authenticationService.authenticate(authenRequest);
//        return ApiResponse.<AuthenticationResponseDTO>builder()
//                .result(AuthenticationResponseDTO.builder()
//                        .authenticated(result).
//                        build())
//                .build();
//    }

}
