package com.login.demo.controller;

import com.login.demo.config.CookieUtil;
import com.login.demo.config.LoginConstant;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping("/super-admin")
public class SuperAdminController {

    @GetMapping
    public String index() {
        return "super-admin";
    }


    @RequestMapping("/logout")
    public String logout(HttpServletResponse httpServletResponse) {
        CookieUtil.clear(httpServletResponse, LoginConstant.JWT_TOKEN_NAME);
        return "redirect:/";
    }
}
