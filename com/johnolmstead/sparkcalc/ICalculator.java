package com.johnolmstead.sparkcalc;

import java.math.BigDecimal;

/**
 * Calculator interface with helper method.
 * Implemented by SimpleCalculator only for now
 */
public interface ICalculator {

    /**
     * Evaluate must be implemented
     * @param expression
     * @return
     */
    BigDecimal evaluate(String expression);

    /**
     *     Calls the operators operation Lamda with the values
     *     @param lhOp left hand operand
     *     @param rhOp right hand operand
     *     @return result of operation
     *
     */
    static BigDecimal doBinaryOperation(BigDecimal lhOp, BigDecimal rhOp,  BinaryOperator operator ) {
        return operator.operation.operate(lhOp, rhOp);
    }
}
