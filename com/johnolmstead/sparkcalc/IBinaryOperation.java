package com.johnolmstead.sparkcalc;

import java.math.BigDecimal;

/**
 * Binary operation lamda interface
 */
interface IBinaryOperation {
    BigDecimal operate(BigDecimal a, BigDecimal b);
}
