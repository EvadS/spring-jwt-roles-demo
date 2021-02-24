package com.login.demo.model.request;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class RegistrationRequest {

    @NotBlank(message = "User email cannot be null")
    @Email(message = "Incorrect email format")
    private String email;

    @NotNull(message = "Password cannot be null")
    @NotBlank(message = "User email cannot be empty")
    private String password;

    public RegistrationRequest() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}