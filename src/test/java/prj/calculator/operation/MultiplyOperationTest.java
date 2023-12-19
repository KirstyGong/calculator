package prj.calculator.operation;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static prj.calculator.model.Operator.DIVISION_OPERATOR;
import static prj.calculator.model.Operator.MULTIPLICATION_OPERATOR;

public class MultiplyOperationTest {
    private static final double DELTA = 0.000001d;

    private MultiplicationOperation multiplicationOperation;
    private IOperationHandler nextHandler;

    @BeforeEach
    public void setup() {
        nextHandler = mock(DivisionOperation.class);
        multiplicationOperation = MultiplicationOperation.getInstance(nextHandler);
    }

    @AfterEach
    public void cleanup() {
        MultiplicationOperation.destroy();
    }

    @Test
    void testWholeNumberInputMultiplication() {
        // Given
        final List<Double> inputs = List.of(2.0, 1.0);

        // When
        final double result = multiplicationOperation.handle(MULTIPLICATION_OPERATOR, inputs);

        // Then
        final double expected = 2;

        assertEquals(expected, result, DELTA);
    }

    @Test
    void testDecimalNumberInputMultiplication() {
        // Given
        final List<Double> inputs = List.of(1.1, 2.2);

        // When
        final double result = multiplicationOperation.handle(MULTIPLICATION_OPERATOR, inputs);

        // Then
        final double expected = 2.42;

        assertEquals(expected, result, DELTA);
    }

    @Test
    void testShouldNotHandleNonMultiplicationCalculation() {
        // Given
        final List<Double> inputs = List.of(1.1, 2.2);

        // When
        when(nextHandler.handle(DIVISION_OPERATOR, inputs)).thenReturn(0.5);
        final double result = multiplicationOperation.handle(DIVISION_OPERATOR, inputs);

        // Then
        final double expected = 2.42;

        assertNotEquals(expected, result, DELTA);
    }

    @Test
    void tesShouldRaiseExceptionNoNextHandler() {
        // Given
        MultiplicationOperation.destroy();
        multiplicationOperation = MultiplicationOperation.getInstance(null);
        final List<Double> inputs = List.of(1.0, 3.0);

        // Then
        assertThrows(IllegalArgumentException.class, () -> multiplicationOperation.handle(DIVISION_OPERATOR, inputs));
    }
}
