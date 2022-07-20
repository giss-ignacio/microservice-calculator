package com.tenpo.calculator.calculator.application;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CalculatorRequestDto {

    @JsonProperty
    List<Double> numbers;

    public List<Double> getNumbers() {
        return numbers;
    }
}
