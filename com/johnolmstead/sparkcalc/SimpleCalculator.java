package com.johnolmstead.sparkcalc;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * SimpleCalculator class
 * does not handle parentheses
 * does not handle right-associative operators
 * handles only binary operators
 */
public class SimpleCalculator implements ICalculator {

    HashMap<String, BinaryOperator> binaryOperations;

    /**
     * Constructor
     * @param binaryOperations
     */
    public SimpleCalculator(HashMap<String, BinaryOperator> binaryOperations) {
        this.binaryOperations = binaryOperations;
    }

    /**
     * Evaluates the expression using two stacks and returns the result using
     * @param expression
     * @return result
     */
    public BigDecimal evaluate(String expression) {
        BigDecimal result;

        StringTokenizer st = new StringTokenizer(expression);
        Stack<BigDecimal> values = new Stack<>();
        Stack<BinaryOperator> operators = new Stack<>();

        String token = "";
        try {
            while (st.hasMoreTokens()) {
                token = st.nextToken();
                try {
                    // handle numerical values
                    BigDecimal value = new BigDecimal(token);
                    values.push(value);
                } catch (NumberFormatException nfe) {
                    // otherwise, assume is a binary operator
                    final BinaryOperator operator = binaryOperations.get(token);
                    if (operator == null) {
                        throw new TokenException();
                    }

                    // while the next operator has less or equal precedence, do a binary operation using the stacks
                    while (!operators.isEmpty() && (operator.precedence <= operators.peek().precedence)) {
                        doBinaryOperation(operators, values);
                    }
                    // push the next operator on
                    operators.push(operator);
                }
            }

            // done parsing, do remaining binary operation using the stacks
            while (!operators.isEmpty()) {
                doBinaryOperation(operators, values);
            }

            // final value should be the result
            result = values.pop();

        } catch (TokenException e) {
            System.out.printf("%s = [ ERROR: Unsupported token '%s' ]\n", expression, token);
            return null;
        } catch (ArithmeticException ae) {
            System.out.printf("%s = [ ERROR: Attempt to divide by zero ]\n", expression);
            return null;
        }catch (Exception e) {
            System.out.printf("%s = [ ERROR: Unexpected exception: %s ]\n", expression, e.toString());
            e.printStackTrace();
            return null;
        }

        return result;
    }

    /**
     * Pops values from stacks, does operation, pushes result back on value stack
     * @param operators
     * @param values
     */
    void doBinaryOperation(Stack<BinaryOperator> operators, Stack<BigDecimal> values) {
        // GOTCHA values are on stack in reverse order.
        // When not taken into account, order dependant operators like - and / fail.
        BigDecimal v2 = values.pop();
        BigDecimal v1 = values.pop();
        BigDecimal result = ICalculator.doBinaryOperation(v1, v2, operators.pop());
        values.push(result);
    }
}
