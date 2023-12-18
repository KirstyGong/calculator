package prj.calculator.operation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

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
        final List<Double> inputs = List.of(2.0, 1.0);

        // When
        final double result = multiplicationOperation.apply(inputs);

        // Then
        final double expected = 2;

        assertEquals(expected, result, DELTA);
    }

    @Test
    void testDecimalNumberInputMultiplication() {
        // Given
        final List<Double> inputs = List.of(1.1, 2.2);

        // When
        final double result = multiplicationOperation.apply(inputs);

        // Then
        final double expected = 2.42;

        assertEquals(expected, result, DELTA);
    }
}
