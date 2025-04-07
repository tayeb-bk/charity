package com.charity2.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/api/test-cors")
    public String testCors() {
        return "API call successful!";
    }
}