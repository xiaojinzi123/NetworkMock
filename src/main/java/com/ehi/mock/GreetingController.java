package com.ehi.mock;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    @RequestMapping("/test")
    public String test() {
        return "teatetstset";
    }

}