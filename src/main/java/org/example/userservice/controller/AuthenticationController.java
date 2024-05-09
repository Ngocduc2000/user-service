package org.example.userservice.controller;

import com.nimbusds.jose.JOSEException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.userservice.dto.*;
import org.example.userservice.service.AuthenticateService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    AuthenticateService authenticationService;

    @PostMapping("/log-in")
    ApiResponse<AuthenticationResponseDTO> authenticate(@RequestBody AuthenticationDTO authenRequest){
        var result = authenticationService.authenticate(authenRequest);
        return ApiResponse.<AuthenticationResponseDTO>builder()
                .result(result)
                .build();
    }

    @PostMapping("/introspect")
    ApiResponse<IntrospectResponseDTO> authenticate(@RequestBody IntrospectRequestDTO request)
            throws ParseException, JOSEException {
        var result = authenticationService.introspect(request);
        return ApiResponse.<IntrospectResponseDTO>builder()
                .result(result)
                .build();
    }

}
