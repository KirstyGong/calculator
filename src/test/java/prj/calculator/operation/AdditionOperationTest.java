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
import static prj.calculator.model.Operator.ADDITION_OPERATOR;
import static prj.calculator.model.Operator.SUBTRACTION_OPERATOR;

public class AdditionOperationTest {

    private static final double DELTA = 0.000001d;

    private AdditionOperation additionOperator;
    private IOperationHandler nextHandler;

    @BeforeEach
    public void setup() {
        nextHandler = mock(SubtractionOperation.class);
        additionOperator = AdditionOperation.getInstance(nextHandler);
    }

    @AfterEach
    public void cleanup() {
        AdditionOperation.destroy();
    }

    @Test
    void testWholeNumberInput() {
        //Given
        final List<Double> input = List.of(1.0, 2.0);

        // When
        final double result = additionOperator.handle(ADDITION_OPERATOR, input);

        //Then
        final double expected = 3;

        assertEquals(expected, result, DELTA);
    }

    @Test
    void testDecimalInput() {
        //Given
        final List<Double> input = List.of(1.1, 2.2);

        // When
        final double result = additionOperator.handle(ADDITION_OPERATOR, input);

        //Then
        final double expected = 3.3;

        assertEquals(expected, result, DELTA);
    }

    @Test
    void testNonAdditionInput() {
        //Given
        final List<Double> input = List.of(1.1, 2.2);

        // When
        when(nextHandler.handle(SUBTRACTION_OPERATOR, input)).thenReturn(-1.1);
        final double result = additionOperator.handle(SUBTRACTION_OPERATOR, input);

        //Then
        final double expected = 3.3;

        assertNotEquals(expected, result, DELTA);
    }

    @Test
    void testShouldRaiseExceptionWhenNoNextHandler() {
        //Given
        AdditionOperation.destroy();
        additionOperator = AdditionOperation.getInstance(null);
        final List<Double> input = List.of(1.1, 2.2);

        //then
        assertThrows(IllegalArgumentException.class, () -> additionOperator.handle(SUBTRACTION_OPERATOR, input));
    }


}
