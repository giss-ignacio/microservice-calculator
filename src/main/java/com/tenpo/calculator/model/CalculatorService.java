package com.tenpo.calculator.model;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalculatorService {

    public Double sum(List<Double> numbers) {
        if (numbers == null) {
            throw new SumException("List of numbers not provided");
        }

        return numbers.stream()
                .reduce(0d, Double::sum);
    }
}
