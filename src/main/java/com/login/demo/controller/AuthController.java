package com.login.demo.controller;


import com.login.demo.config.CustomUserDetails;
import  com.login.demo.entity.User;
import com.login.demo.config.jwt.JwtProvider;
import com.login.demo.exception.UserLoginException;
import com.login.demo.model.request.AuthRequest;
import com.login.demo.model.request.RegistrationRequest;
import com.login.demo.model.response.JwtAuthenticationResponse;
import com.login.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final JwtProvider jwtProvider;
    private final  AuthenticationManager authenticationManager;

    private final Logger logger = LoggerFactory.getLogger(AuthController.class);

    public AuthController(UserService userService, JwtProvider jwtProvider, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.jwtProvider = jwtProvider;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")
    public ResponseEntity registerUser(@Validated  @RequestBody @Valid RegistrationRequest registrationRequest) {

        // the best  simplier way
        User u = new User();
        u.setPassword(registrationRequest.getPassword());
        u.setEmail(registrationRequest.getEmail());
        userService.saveUser(u);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/auth")
    public  ResponseEntity auth(@Validated @RequestBody AuthRequest loginRequest) {

        Optional<Authentication> authenticationOpt = Optional.ofNullable(authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getLogin(),
                        loginRequest.getPassword())));

        Authentication authentication = authenticationOpt.orElseThrow(() -> new UserLoginException("Couldn't login user [" + loginRequest + "]"));

        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        logger.info("Logged in User returned [API]: " + customUserDetails.getUsername());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwtToken = jwtProvider.generateToken(customUserDetails);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwtToken, jwtProvider.getJwtExpirationInMs()));

    }
}
