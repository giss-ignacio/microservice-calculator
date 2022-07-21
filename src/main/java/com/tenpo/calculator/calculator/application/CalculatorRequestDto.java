package com.tenpo.calculator.calculator.application;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class CalculatorRequestDto {
    @JsonProperty
    private List<Double> numbers;

    public List<Double> getNumbers() {
        return numbers;
    }

    public void setNumbers(List<Double> numbers) {
        this.numbers = new ArrayList<>(numbers);
    }
}
