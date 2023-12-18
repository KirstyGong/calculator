package prj.calculator.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class InputValidatorTest {

    private InputValidator inputValidator;

    @BeforeEach
    public void setup() {
        inputValidator = InputValidator.getInstance();
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {
            " ",
            "t",
            "\n"
    })
    void testCanFailNullAndEmpty(String input) {
        assertThrows(IllegalArgumentException.class, () -> inputValidator.validate(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "1+1",
            "1-1",
            "1*1",
            "1/1"
    })
    void testCanPassValidWholeNumberInput(String input) {
        assertTrue(inputValidator.validate(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "1.1+1.1"
    })
    void testCanPassValidDecimalNumberInput(String input) {
        assertTrue(inputValidator.validate(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "-",
            "0",
            "1-+1",
            "1-",
            "9999999999+9",
            "9+9999999999"
    })
    void testCanFailInValidInput(String input) {
        assertThrows(IllegalArgumentException.class, () -> inputValidator.validate(input));
    }
}
