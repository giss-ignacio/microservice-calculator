package com.tenpo.calculator.calculator.application;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculatorController {
    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "Add two numbers. Returns the result of the sum. Needs authentication.",
            produces = "text/plain")
    public String helloWorld() {
        return "Hello World";
    }

    @RequestMapping(value = "/goodbye", method = RequestMethod.GET)
    public String goodbye() {
        return "Hello goodbye";
    }
}
