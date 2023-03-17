package com.ahmed.Calculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculatorController {
    @Autowired
    private Calculator calculator;

    @GetMapping(
            value = "/calculate-sum",
            produces = "application/json; charset=utf-8")
    public String sum(@RequestParam Integer a,
                      @RequestParam Integer b){
        return String.valueOf(calculator.sum(a, b));
    }
}
