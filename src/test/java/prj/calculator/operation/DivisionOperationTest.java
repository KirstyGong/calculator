package prj.calculator.operation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DivisionOperationTest {
    private static final double DELTA = 0.000001d;


    private DivisionOperation divisionOperator;

    @BeforeEach
    public void setup() {
        divisionOperator = DivisionOperation.getInstance();
    }

    @Test
    void testWholeNumberInputDivision() {
        // Given
        final double dividend = 4;
        final double divisor = 2;

        // When
        final double result = divisionOperator.apply(dividend, divisor);

        // Then
        final double expected = 2;
        assertEquals(expected, result, DELTA);
    }

    @Test
    void testDecimalInputDivision() {
        // Given
        final double dividend = 2.42;
        final double divisor = 2.2;

        // When
        final double result = divisionOperator.apply(dividend, divisor);

        // Then
        final double expected = 1.1;

        assertEquals(expected, result, DELTA);
    }

    @Test
    void testInputDivisionWithNonDivisibleResult() {

        // Given
        final double dividend = 1.0;
        final double divisor = 3.0;

        // When
        final double result = divisionOperator.apply(dividend, divisor);

        // Then
        final double expected = 0.333333d;

        assertEquals(expected, result, DELTA);
    }

    @Test
    void tesShouldThrowExceptionWhenNonZeroDivideZero() {
        // Given
        final double dividend = 3.0;
        final double divisor = 0;

        // Then
        assertThrows(IllegalArgumentException.class, () -> divisionOperator.apply(dividend, divisor));
    }
}
