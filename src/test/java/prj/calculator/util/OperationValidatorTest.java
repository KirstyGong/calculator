package prj.calculator.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OperationValidatorTest {

    private OperationValidator operationValidator;

    @BeforeEach
    public void setup() {
        operationValidator = OperationValidator.getInstance();
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {
            " ",
            "\t",
            "\n"
    })
    void testCanFailNullAndEmptyOperatorInput(String input) {
        //Then
        assertFalse(operationValidator.validate(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "+",
            "-",
            "*",
            "/",
            " +",
            "+ "
    })
    void testCanPassValidOperatorInput(String input) {
        //Then
        assertTrue(operationValidator.validate(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "s",
            "-+",
    })
    void testCanFailInvalidOperatorInput(String input) {
        //Then
        assertFalse(operationValidator.validate(input));
    }

}
