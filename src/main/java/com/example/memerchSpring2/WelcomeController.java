package com.example.memerchSpring2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {
    private static final String template = "Hello, %s!";

    @GetMapping("/welcome")
    public Welcome welcome(@RequestParam(value = "name", defaultValue = "customer") String name) {
        return new Welcome(String.format(template, name));
    }
}
