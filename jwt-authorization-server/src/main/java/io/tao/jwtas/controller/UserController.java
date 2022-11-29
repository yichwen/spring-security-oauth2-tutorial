package io.tao.jwtas.controller;

import io.tao.jwtas.dto.R;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/user")
    public R getUserDetails(Authentication authentication) {
        if (authentication != null) {
            return R.ok()
                    .put("name", authentication.getName())
                    .put("authorities", authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority));
        } else {
            return R.error(HttpStatus.FORBIDDEN, "Forbidden");
        }
    }
}
