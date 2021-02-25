package com.login.demo.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

public class StartupRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Command Line Runner class invoked!!");
    }
}