package prj.calculator.model;

import java.util.Arrays;

public enum Operator {

    ADDITION_OPERATOR("+"),
    SUBTRACTION_OPERATOR("-"),
    MULTIPLICATION_OPERATOR("*"),
    DIVISION_OPERATOR("/");

    final String value;

    Operator(
            final String value
    ) {
        this.value = value;
    }

    public static Operator getEnum(
            final String value
    ) {
        return Arrays.stream(values()).filter(operator -> operator.value.equals(value)).findAny().orElseThrow(IllegalArgumentException::new);
    }

    public String value() {
        return this.value;
    }

    @Override
    public String toString() {
        return value;
    }
}
