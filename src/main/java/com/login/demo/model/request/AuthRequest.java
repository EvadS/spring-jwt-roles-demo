package com.login.demo.model.request;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AuthRequest {
    @NotBlank(message = "User email cannot be null")
    @Email(message = "Incorrect email format")
    private String login;

    @NotNull(message = "Password cannot be null")
    @NotBlank(message = "User email cannot be empty")
    private String password;

    public AuthRequest() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}