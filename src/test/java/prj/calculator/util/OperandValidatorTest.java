package prj.calculator.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OperandValidatorTest {

    private OperandValidator operandValidator;

    @BeforeEach
    public void setup() {
        operandValidator = OperandValidator.getInstance();
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {
            " ",
            "\t",
            "\n"
    })
    void testCanFailNullAndEmptyOperandInput(String input) {
        assertFalse(operandValidator.validate(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "1",
            "1.1"
    })
    void testCanPassValidOperandInput(String input) {
        //When
        boolean result = operandValidator.validate(input);

        //Then
        assertTrue(result);
    }

    @Test
    void testCanFailInvalidOperandInput() {
        //Given
        String input = "a";

        //When
        boolean result = operandValidator.validate(input);

        //Then
        assertFalse(result);
    }


}
