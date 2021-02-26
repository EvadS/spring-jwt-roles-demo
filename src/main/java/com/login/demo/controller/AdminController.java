package com.login.demo.controller;

import com.login.demo.config.CookieUtil;
import com.login.demo.config.LoginConstant;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping("/admin")
public class AdminController {

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public String index() {
        return "admin";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletResponse httpServletResponse) {
        CookieUtil.clear(httpServletResponse, LoginConstant.JWT_TOKEN_NAME);
        return "redirect:/";
    }
}
