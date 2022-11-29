package io.tao.jwtas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JwtAuthorizationServer {
    public static void main(String[] args) {
        SpringApplication.run(JwtAuthorizationServer.class, args);
    }
}
