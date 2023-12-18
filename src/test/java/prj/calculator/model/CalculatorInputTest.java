package prj.calculator.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static prj.calculator.model.Operator.ADDITION_OPERATOR;

public class CalculatorInputTest {
    private static final List<Double> OPERANDS = List.of(1.0, 2.0);
    private static final Operator OPERATOR = ADDITION_OPERATOR;

    private CalculatorInput calculatorInput;

    @BeforeEach
    public void setup() {
        calculatorInput = new CalculatorInput(OPERANDS, OPERATOR);
    }

    @Test
    void testCanGetOperands() {
        assertEquals(OPERANDS, calculatorInput.getOperands());
    }

    @Test
    void testCanGetOperation() {
        assertEquals(OPERATOR, calculatorInput.getOperator());
    }
}
