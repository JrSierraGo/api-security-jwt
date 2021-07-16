package com.security.jwt.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/greetings")
public class GreetingController {

    @RequestMapping("/hello")
    public String getGreeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return "Hello ".concat(name).concat("!!!");
    }
}
