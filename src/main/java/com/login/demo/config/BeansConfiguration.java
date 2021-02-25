package com.login.demo.config;


import com.login.demo.config.jwt.JwtConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfiguration {

    @Bean
    public JwtConfig jwtConfig() {
        return new JwtConfig();
    }

    @Bean
    public StartupRunner StartupRunner() {
        return new StartupRunner();
    }
}
