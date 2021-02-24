package com.login.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/super-admin")
public class SuperAdminController {
    @GetMapping("/")
    public  String index(){
        return "super-admin";
    }
}
