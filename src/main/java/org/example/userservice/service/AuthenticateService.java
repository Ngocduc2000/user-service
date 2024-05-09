package org.example.userservice.service;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.example.userservice.dto.AuthenticationDTO;
import org.example.userservice.dto.AuthenticationResponseDTO;
import org.example.userservice.dto.IntrospectRequestDTO;
import org.example.userservice.dto.IntrospectResponseDTO;
import org.example.userservice.exception.AppException;
import org.example.userservice.exception.ErrorCode;
import org.example.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.HealthContributorRegistry;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticateService {
    UserRepository userRepository;

    @NonFinal
    @Value("${jwt.signerKey}")
    protected String SIGNER_KEY;

    public AuthenticationResponseDTO authenticate(AuthenticationDTO authenRequest){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        var user = userRepository.findByUsername(authenRequest.getUsername())
                .orElseThrow(()-> new AppException(ErrorCode.USER_NOT_EXISTED));
        boolean authenticated = passwordEncoder.matches(authenRequest.getPassword(), user.getPassword());

        if (!authenticated){
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }

        var token = generateToken(authenRequest.getUsername());
        return AuthenticationResponseDTO.builder()
                .token(token)
                .authenticated(true)
                .build();
    }

    /*
    * Ham tao token
    * token co header, payload
    * header chua thuat toan ma hoa
    * payload chua body cua token
    * */
    private String generateToken(String username){
        //tao header
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS256);
        //tao payload
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(username) //user dang nhap
                .issuer("example.com")
                .issueTime(new Date())
                .expirationTime(new Date(Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()))
                .claim("customClaim","customValue")
                .build();
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(jwsHeader, payload);

        //ky token
        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e){
            log.error("Cannot create token", e);
            throw new AppException(ErrorCode.CAN_NOT_GENERATE_TOKEN);
        }

    }

    //kiem tra token
    public IntrospectResponseDTO introspect(IntrospectRequestDTO requestDTO) throws ParseException, JOSEException {
        var token = requestDTO.getToken();
        JWSVerifier jwsVerifier = new MACVerifier(SIGNER_KEY.getBytes());
        SignedJWT signedJWT = SignedJWT.parse(token);

        //kiem tra token het han chua
        Date expirationTime = signedJWT.getJWTClaimsSet().getExpirationTime();
        var verified = signedJWT.verify(jwsVerifier);
        return IntrospectResponseDTO.builder().
                valid(verified && expirationTime.after(new Date()))
                .build();
    }
}

