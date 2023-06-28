package com.rewards.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Data
public class ApiError {

    HttpStatus errorCode;
    String errorMessage;
}
