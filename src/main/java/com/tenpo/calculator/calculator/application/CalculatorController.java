package com.tenpo.calculator.calculator.application;

import com.tenpo.calculator.model.CalculatorService;
import com.tenpo.calculator.model.SumException;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculatorController {

    @Autowired
    private CalculatorService calculatorService;

    @RequestMapping(value = "/sum-numbers", method = RequestMethod.POST)
    @ApiOperation(value = "Add two numbers. Returns the result of the sum. Needs authentication.",
            produces = "text/plain")
    @ResponseBody
    public ResponseEntity<?> sumNumbers(@RequestBody CalculatorRequestDto sumRequest) {
        CalculatorResponseDto calculatorResponseDto = new CalculatorResponseDto();
        try {
            calculatorResponseDto.setNumbersSum(
                    calculatorService.sum(sumRequest.getNumbers())
            );
            return  ResponseEntity.ok(calculatorResponseDto);
        } catch (SumException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @RequestMapping(value = "/goodbye", method = RequestMethod.GET)
    public String goodbye() {
        return "Hello goodbye";
    }
}
