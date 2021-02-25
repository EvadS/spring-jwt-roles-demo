package com.login.demo.controller;

import com.login.demo.config.CookieUtil;
import com.login.demo.config.CustomUserDetails;
import com.login.demo.config.LoginConstant;
import com.login.demo.config.jwt.JwtProvider;
import com.login.demo.exception.UserLoginException;
import com.login.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Controller
public class LoginController {
    private final UserService userService;
    private final JwtProvider jwtProvider;
    private final AuthenticationManager authenticationManager;

    private final Logger logger = LoggerFactory.getLogger(LoginController.class);

    public LoginController(UserService userService, JwtProvider jwtProvider, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.jwtProvider = jwtProvider;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/login")
    public String home() {
        return "login";
    }

    @PostMapping("/login")
    public String login(HttpServletResponse httpServletResponse, String username, String password, String redirect, Model model) {

        Optional<Authentication> authenticationOpt = Optional.ofNullable(authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)));

        Authentication authentication = authenticationOpt.orElseThrow(
                () -> new UserLoginException("Couldn't login user [" + username + "]"));

        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        logger.info("Logged in User returned [API]: " + customUserDetails.getUsername());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwtToken = jwtProvider.generateToken(customUserDetails);
        CookieUtil.create(httpServletResponse, LoginConstant.JWT_TOKEN_NAME, jwtToken, false, -1, "localhost");

        return "redirect:" + redirectDependOffUserRole(customUserDetails);
    }

    private String redirectDependOffUserRole(CustomUserDetails customUserDetails) {

        if (customUserDetails.getRoles().stream().anyMatch(item -> item.isAdminRole())) {
            return "super-admin";
        }

        else if (customUserDetails.getRoles().stream().anyMatch(item -> item.isAdminRole())) {
            return "admin";
        }

        return "user";
    }
}
