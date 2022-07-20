package com.tenpo.calculator.calculator.application;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CalculatorResponseDto {
    @JsonProperty
    Double numbersSum;

    public CalculatorResponseDto(){
    }

    public void setNumbersSum(Double numbersSum) {
        this.numbersSum = numbersSum;
    }
}
