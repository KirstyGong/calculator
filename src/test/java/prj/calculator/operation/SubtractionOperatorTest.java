package prj.calculator.operation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SubtractionOperatorTest {
    private static final double DELTA = 0.000001d;

    private SubtractionOperation subtractionOperator;

    @BeforeEach
    public void setup() {
        subtractionOperator = SubtractionOperation.getInstance();
    }

    @Test
    void testWholeNumberInputSubtraction() {
        //Given
        final List<Double> inputs = List.of(3.0, 1.0);

        // When
        final double result = subtractionOperator.apply(inputs);

        //Then
        final double expected = 2;

        assertEquals(expected, result, DELTA);
    }

    @Test
    void testDecimalInputSubtraction() {
        //Given
        final List<Double> inputs = List.of(3.3, 1.1);

        // When
        final double result = subtractionOperator.apply(inputs);

        //Then
        final double expected = 2.2;

        assertEquals(expected, result, DELTA);
    }

    @Test
    void testSubtractionWithNegativeResult() {
        //Given
        final List<Double> inputs = List.of(1.1, 3.3);

        // When
        final double result = subtractionOperator.apply(inputs);

        //Then
        final double expected = -2.2;

        assertEquals(expected, result, DELTA);
    }
}
