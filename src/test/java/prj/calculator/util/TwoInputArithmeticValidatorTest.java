package prj.calculator.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TwoInputArithmeticValidatorTest {

    private TwoInputArithmeticValidator twoInputArithmeticValidator;

    @BeforeEach
    public void setup() {
        twoInputArithmeticValidator = TwoInputArithmeticValidator.getInstance();
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {
            " ",
            "t",
            "\n"
    })
    void testCanFailNullAndEmpty(String input) {
        assertThrows(IllegalArgumentException.class, () -> twoInputArithmeticValidator.validate(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "1+2",
            "1-30",
            "21*4",
            "1/5"
    })
    void testCanPassValidWholeNumberInput(String input) {
        assertTrue(twoInputArithmeticValidator.validate(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "1.1+1.2"
    })
    void testCanPassValidDecimalNumberInput(String input) {
        assertTrue(twoInputArithmeticValidator.validate(input));
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
        assertThrows(IllegalArgumentException.class, () -> twoInputArithmeticValidator.validate(input));
    }
}
