package com.johnolmstead.sparkcalc;

/**
 * Encapsulates a binary operator
 * Contains string value, precedence value and lamda for the operation
 */
public class BinaryOperator {
    public final IBinaryOperation operation;
    public final int precedence;
    private final String stringValue;

    /**
     * Constructor
     * @param stringValue
     * @param operation
     * @param precedence
     */
    public BinaryOperator(String stringValue, IBinaryOperation operation, int precedence) {
        this.operation = operation;
        this.stringValue = stringValue;
        this.precedence = precedence;
    }

    @Override
    public String toString() {
        return this.stringValue;
    }

}