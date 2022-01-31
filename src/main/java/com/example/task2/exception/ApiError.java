package com.example.task2.exception;

import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
public class ApiError {

    private List<String> errors;

    public ApiError(final List<String> errors) {
        this.errors = errors;
    }

    public ApiError(final String error) {
        errors = Arrays.asList(error);
    }
}