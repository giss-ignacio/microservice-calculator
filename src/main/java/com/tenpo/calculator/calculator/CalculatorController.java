package com.tenpo.calculator.calculator;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculatorController {
    @RequestMapping
    public String helloWorld() {
        return "Hello World";
    }

    @RequestMapping("/goodbye")
    public String goodbye() {
        return "Hello goodbye";
    }
}
