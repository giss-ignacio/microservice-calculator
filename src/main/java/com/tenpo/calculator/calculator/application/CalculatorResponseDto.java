package com.tenpo.calculator.calculator.application;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

public class CalculatorResponseDto {
    @JsonProperty
    @ApiModelProperty(notes = "Sum result", example = "123.345")
    private Double numbersSum;

    public CalculatorResponseDto(){
    }

    public void setNumbersSum(Double numbersSum) {
        this.numbersSum = numbersSum;
    }

    public Double getNumbersSum() {
        return numbersSum;
    }
}
