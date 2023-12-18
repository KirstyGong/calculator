package prj.calculator.operation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

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
        final List<Double> inputs = List.of(4.0, 2.0);

        // When
        final double result = divisionOperator.apply(inputs);

        // Then
        final double expected = 2;
        assertEquals(expected, result, DELTA);
    }

    @Test
    void testDecimalInputDivision() {
        // Given
        final List<Double> inputs = List.of(2.42, 2.2);

        // When
        final double result = divisionOperator.apply(inputs);

        // Then
        final double expected = 1.1;

        assertEquals(expected, result, DELTA);
    }

    @Test
    void testInputDivisionWithNonDivisibleResult() {

        // Given
        final List<Double> inputs = List.of(1.0, 3.0);

        // When
        final double result = divisionOperator.apply(inputs);

        // Then
        final double expected = 0.333333d;

        assertEquals(expected, result, DELTA);
    }

    @Test
    void tesShouldThrowExceptionWhenNonZeroDivideZero() {
        // Given
        final List<Double> inputs = List.of(1.0, 0.0);

        // Then
        assertThrows(IllegalArgumentException.class, () -> divisionOperator.apply(inputs));
    }
}
