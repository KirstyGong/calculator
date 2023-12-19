package prj.calculator.operation;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static prj.calculator.model.Operator.DIVISION_OPERATOR;
import static prj.calculator.model.Operator.MULTIPLICATION_OPERATOR;

public class DivisionOperationTest {
    private static final double DELTA = 0.000001d;


    private DivisionOperation divisionOperator;
    private IOperationHandler nextHandler;

    @BeforeEach
    public void setup() {
        nextHandler = mock(MultiplicationOperation.class);
        divisionOperator = DivisionOperation.getInstance(nextHandler);
    }

    @AfterEach
    public void cleanup() {
        DivisionOperation.destroy();
    }

    @Test
    void testWholeNumberInputDivision() {
        // Given
        final List<Double> inputs = List.of(4.0, 2.0);

        // When
        final double result = divisionOperator.handle(DIVISION_OPERATOR, inputs);

        // Then
        final double expected = 2;
        assertEquals(expected, result, DELTA);
    }

    @Test
    void testDecimalInputDivision() {
        // Given
        final List<Double> inputs = List.of(2.42, 2.2);

        // When
        final double result = divisionOperator.handle(DIVISION_OPERATOR, inputs);

        // Then
        final double expected = 1.1;

        assertEquals(expected, result, DELTA);
    }

    @Test
    void testInputDivisionWithNonDivisibleResult() {

        // Given
        final List<Double> inputs = List.of(1.0, 3.0);

        // When
        final double result = divisionOperator.handle(DIVISION_OPERATOR, inputs);

        // Then
        final double expected = 0.333333d;

        assertEquals(expected, result, DELTA);
    }

    @Test
    void tesShouldThrowExceptionWhenNonZeroDivideZero() {
        // Given
        final List<Double> inputs = List.of(1.0, 0.0);

        // Then
        assertThrows(IllegalArgumentException.class, () -> divisionOperator.handle(DIVISION_OPERATOR, inputs));
    }

    @Test
    void tesShouldNotHandleNonDivisionCalculation() {
        // Given
        final List<Double> inputs = List.of(1.0, 3.0);

        // When
        when(nextHandler.handle(MULTIPLICATION_OPERATOR, inputs)).thenReturn(3.0);
        final double result = divisionOperator.handle(MULTIPLICATION_OPERATOR, inputs);

        // Then
        final double expected = 0.333333d;

        assertNotEquals(expected, result, DELTA);
    }

    @Test
    void tesShouldRaiseExceptionNoNextHandler() {
        // Given
        DivisionOperation.destroy();
        divisionOperator = DivisionOperation.getInstance(null);
        final List<Double> inputs = List.of(1.0, 3.0);

        // Then
        assertThrows(IllegalArgumentException.class, () -> divisionOperator.handle(MULTIPLICATION_OPERATOR, inputs));
    }

}
