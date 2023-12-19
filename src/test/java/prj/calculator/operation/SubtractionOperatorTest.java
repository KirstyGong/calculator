package prj.calculator.operation;

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
import static prj.calculator.model.Operator.SUBTRACTION_OPERATOR;

public class SubtractionOperatorTest {
    private static final double DELTA = 0.000001d;

    private SubtractionOperation subtractionOperator;
    private IOperationHandler nextHandler;

    @BeforeEach
    public void setup() {
        nextHandler = mock(MultiplicationOperation.class);
        subtractionOperator = SubtractionOperation.getInstance(nextHandler);
    }

    @Test
    void testWholeNumberInputSubtraction() {
        //Given
        final List<Double> inputs = List.of(3.0, 1.0);

        // When
        final double result = subtractionOperator.handle(SUBTRACTION_OPERATOR, inputs);

        //Then
        final double expected = 2;

        assertEquals(expected, result, DELTA);
    }

    @Test
    void testDecimalInputSubtraction() {
        //Given
        final List<Double> inputs = List.of(3.3, 1.1);

        // When
        final double result = subtractionOperator.handle(SUBTRACTION_OPERATOR, inputs);

        //Then
        final double expected = 2.2;

        assertEquals(expected, result, DELTA);
    }

    @Test
    void testSubtractionWithNegativeResult() {
        //Given
        final List<Double> inputs = List.of(1.1, 3.3);

        // When
        final double result = subtractionOperator.handle(SUBTRACTION_OPERATOR, inputs);

        //Then
        final double expected = -2.2;

        assertEquals(expected, result, DELTA);
    }

    @Test
    void testShouldNotHandleNonSubtractionCalculation() {
        //Given
        final List<Double> inputs = List.of(1.1, 3.3);

        // When
        when(nextHandler.handle(MULTIPLICATION_OPERATOR, inputs)).thenReturn(3.3);
        final double result = subtractionOperator.handle(DIVISION_OPERATOR, inputs);

        //Then
        final double expected = -2.2;

        assertNotEquals(expected, result, DELTA);
    }

    @Test
    void tesShouldRaiseExceptionNoNextHandler() {
        // Given
        SubtractionOperation.destroy();
        subtractionOperator = SubtractionOperation.getInstance(null);
        final List<Double> inputs = List.of(1.0, 3.0);

        // Then
        assertThrows(IllegalArgumentException.class, () -> subtractionOperator.handle(MULTIPLICATION_OPERATOR, inputs));
    }
}
