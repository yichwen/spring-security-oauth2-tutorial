package io.tao.jwtas.controller;

import io.tao.jwtas.dto.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class ResponseController {

    @GetMapping("/response")
    public R getResponse() {
        return R.error();
    }

}
