package com.tenpo.calculator.calculator.application;

import com.tenpo.calculator.model.CalculatorService;
import com.tenpo.calculator.model.SumException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
    @ResponseBody
    @ApiOperation(value = "Add two numbers. Returns the result of the sum. Needs authentication.",
            produces = "text/plain")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request - The sum could not be done"),
            @ApiResponse(code = 401, message = "Unauthorized - User needs to be logged in")
    })
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
