package com.tenpo.calculator.calculator.application;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

public class CalculatorRequestDto {
    @JsonProperty
    @ApiModelProperty(notes = "List of numbers", example = "[1.1, 2.2, 3.3]")
    private List<Double> numbers;

    public List<Double> getNumbers() {
        return numbers;
    }

    public void setNumbers(List<Double> numbers) {
        this.numbers = new ArrayList<>(numbers);
    }
}
