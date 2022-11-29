package io.tao.rs.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceController {
    
    @GetMapping(value="/user")
    public String getUser() {
        return "user";
    }

    @GetMapping(value="/admin")
    public String getAdmin() {
        return "admin";
    }

}
