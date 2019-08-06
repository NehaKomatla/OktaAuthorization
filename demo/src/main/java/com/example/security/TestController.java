package com.example.security;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/check/")
public class TestController {

    @RequestMapping(method = RequestMethod.GET, value = "greet")
    public String greet() {
        System.err.println("Good morning !");

        return "Hi !!";
    }

}
