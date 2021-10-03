package com.skyrimmarket.backend.web.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

public class BadRequestException extends ResponseStatusException {
    public BadRequestException(String reason) {
        super(BAD_REQUEST, reason);
    }
}
