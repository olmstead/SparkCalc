package com.johnolmstead.sparkcalc;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;

/**
 * SparkCalc entry point for application
 *
 * Accepts an array of expressions to be evaluated from the command line
 */
public class SparkCalc {
    /**
     * program main
     * @param args array of expressions to be evaluated
     */
    public static void main(String args[]){

        final int PRECISION = 4;

        // set of operators
        HashMap<String, BinaryOperator> operators = new HashMap<>();
        operators.put("+", new BinaryOperator("+", (a, b) -> a.add(b), 200));
        operators.put("-", new BinaryOperator("-", (a, b) -> a.subtract(b), 200));
        operators.put("*", new BinaryOperator("*", (a, b) -> a.multiply(b), 300));
        operators.put("/", new BinaryOperator("/", (a, b) -> a.divide(b, PRECISION, RoundingMode.HALF_UP), 300));
        // NOTE! operators a and b are reversed on the rhs of the lambas for subtraction and division.
        // This is becuase values are pulled from a stack in reverse order

        // add more operators here

        // inject with operators
        SimpleCalculator calculator = new SimpleCalculator(operators);

        // evaluate expressions from command line
        for (int i = 0; i < args.length; i++) {
            final String expression = args[i];
            BigDecimal result = calculator.evaluate(expression);
            if (result != null) {
                BigDecimal scaledResult = result.setScale(PRECISION);
                System.out.printf("\n%s = %." + PRECISION + "f\n", expression, scaledResult);
            }
        }
    }
}
