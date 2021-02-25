package com.login.demo.controller;


import com.login.demo.entity.User;
import com.login.demo.model.request.RegistrationRequest;
import com.login.demo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String home(Model model) {
        return "register";
    }

    @PostMapping()
    public String registerUser(@Valid RegistrationRequest registrationRequest, Model model) {

        // the best  simplier way
        User u = new User();
        u.setPassword(registrationRequest.getPassword());
        u.setEmail(registrationRequest.getEmail());
        userService.saveUser(u);

        return "redirect:/login";
    }
}
