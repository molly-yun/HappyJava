package com.example.main;

import com.example.util.Calculator;

public class CalculatorMain {
    public static void main(String[] args) {
        Calculator cal = new Calculator();
        int value = cal.plus(50, 100);
        System.out.println(value);

        com.example.util2.Calculator cal2 = new com.example.util2.Calculator();
        int value2 = cal2.devide(100, 50);
        System.out.println(value2);
    }
}
