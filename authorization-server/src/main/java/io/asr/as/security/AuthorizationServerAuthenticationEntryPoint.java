package io.asr.as.security;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Data;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

public class AuthorizationServerAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(new ErrorResponse("Access denied"));
        System.out.println(json);
        response.getWriter().write(json);
        response.setHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(401);
    }

    @Data
    private class ErrorResponse {
        private LocalDateTime now = LocalDateTime.now();
        private String message;

        public ErrorResponse(String message) {
            this.message = message;
        }
    }

}
