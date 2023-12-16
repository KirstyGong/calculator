package prj.calculator.operation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdditionOperationTest {
    private static final double DELTA = 0.000001d;

    private AdditionOperation additionOperator;

    @BeforeEach
    public void setup() {
        additionOperator = AdditionOperation.getInstance();
    }

    @Test
    void testWholeNumberInput() {
        //Given
        final double a = 1;
        final double b = 2;

        // When
        final double result = additionOperator.apply(a, b);

        //Then
        final double expected = 3;

        assertEquals(expected, result, DELTA);
    }

    @Test
    void testDecimalInput() {
        //Given
        final double a = 1.1;
        final double b = 2.2;

        // When
        final double result = additionOperator.apply(a, b);

        //Then
        final double expected = 3.3;

        assertEquals(expected, result, DELTA);
    }
}
