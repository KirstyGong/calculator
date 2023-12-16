package prj.calculator.operation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MultiplyOperationTest {
    private static final double DELTA = 0.000001d;

    private MultiplicationOperation multiplicationOperation;

    @BeforeEach
    public void setup() {
        multiplicationOperation = MultiplicationOperation.getInstance();
    }

    @Test
    void testWholeNumberInputMultiplication() {
        // Given
        final double multiplicand = 1;
        final double multiplier = 2;

        // When
        final double result = multiplicationOperation.apply(multiplicand, multiplier);

        // Then
        final double expected = 2;

        assertEquals(expected, result, DELTA);
    }

    @Test
    void testDecimalNumberInputMultiplication() {
        // Given
        final double multiplicand = 1.1;
        final double multiplier = 2.2;

        // When
        final double result = multiplicationOperation.apply(multiplicand, multiplier);

        // Then
        final double expected = 2.42;

        assertEquals(expected, result, DELTA);
    }
}
