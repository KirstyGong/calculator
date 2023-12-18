package prj.calculator.operation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

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
        final List<Double> input = List.of(1.0, 2.0);

        // When
        final double result = additionOperator.apply(input);

        //Then
        final double expected = 3;

        assertEquals(expected, result, DELTA);
    }

    @Test
    void testDecimalInput() {
        //Given
        final List<Double> input = List.of(1.1, 2.2);

        // When
        final double result = additionOperator.apply(input);

        //Then
        final double expected = 3.3;

        assertEquals(expected, result, DELTA);
    }
}
