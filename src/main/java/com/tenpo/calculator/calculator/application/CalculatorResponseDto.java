package com.tenpo.calculator.calculator.application;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CalculatorResponseDto {
    @JsonProperty
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
