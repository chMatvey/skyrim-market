package com.skyrimmarket.backend.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

import static java.lang.String.valueOf;
import static java.time.LocalDateTime.now;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public class ErrorUtil {
    public static void addErrorBodyToResponse(HttpServletResponse response, HttpStatus status, Exception exception) throws IOException {
        response.setContentType(APPLICATION_JSON_VALUE);
        response.setHeader("error", exception.getMessage());
        response.setStatus(status.value());

        HashMap<String, String> fields = new HashMap<>();
        fields.put("message", exception.getMessage());
        fields.put("time", now().toString());
        fields.put("status", valueOf(status.value()));
        fields.put("reason", status.getReasonPhrase());

        new ObjectMapper().writeValue(response.getOutputStream(), fields);
    }
}
