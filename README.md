# SparkCalc
SparkCalc.java contains the main entry point fro the application.

# Usage
- Accepts a list of expressions at the command line
- Expression values and operators must be separated by spaces

> java SparkCalc "2 + 3 - 10 + 1 * 40" "6 * 8 / 12 * 2"

# Customization
New operators may be added to a hashmap in SparkCalc.java by providing a BinaryOperator instance with a token, lamda and precedence value. For exmaple:

```java
        operators.put("?", new BinaryOperator("*", (a, b) -> a.multiply(b).add(a), 500));
```
Adds an operator wth the following attributes:
- token: ?
- lamda: (a, b) -> a.multiply(b).add(a)
- priority: 500