package prj.calculator.operation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        final double minuend = 3;
        final double subtrahend = 1;

        // When
        final double result = subtractionOperator.apply(minuend, subtrahend);

        //Then
        final double expected = 2;

        assertEquals(expected, result, DELTA);
    }

    @Test
    void testDecimalInputSubtraction() {
        //Given
        final double minuend = 3.3;
        final double subtrahend = 1.1;

        // When
        final double result = subtractionOperator.apply(minuend, subtrahend);

        //Then
        final double expected = 2.2;

        assertEquals(expected, result, DELTA);
    }

    @Test
    void testSubtractionWithNegativeResult() {
        //Given
        final double minuend = 1.1;
        final double subtrahend = 3.3;

        // When
        final double result = subtractionOperator.apply(minuend, subtrahend);

        //Then
        final double expected = -2.2;

        assertEquals(expected, result, DELTA);
    }
}
