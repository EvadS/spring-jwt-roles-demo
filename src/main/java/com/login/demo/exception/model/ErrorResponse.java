package com.login.demo.exception.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;



public class ErrorResponse {

    private HttpStatus status;
    private int code;

    /**
     * property holds a user-friendly message about the apierror.
     */
    private String message;
    /**
     * system message describing the api error in more detail.
     */
    private List<String> details = new ArrayList<>();

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy.MM.dd'T'HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime timestamp = LocalDateTime.now();


    public ErrorResponse() {
    }

    public ErrorResponse(HttpStatus status, String message, List<String> details) {
        this.status = status;
        this.code = status.value();
        this.message = message;
        this.details = details;
    }

    public ErrorResponse(HttpStatus status, String message) {
        this.status = status;
        this.code = status.value();
        this.message = message;
    }


    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getDetails() {
        return details;
    }

    public void setDetails(List<String> details) {
        this.details = details;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", details=" + details +
                ", timestamp=" + timestamp +
                '}';
    }
}