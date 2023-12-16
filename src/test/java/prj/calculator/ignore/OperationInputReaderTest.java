package prj.calculator.ignore;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import prj.calculator.ignore.OperationIInputReader;
import prj.calculator.util.OperandValidator;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OperationInputReaderTest {

    private OperationIInputReader operationInputReader;
    private OperandValidator operandValidator;

    @BeforeEach
    public void setup() {
        operationInputReader = OperationIInputReader.getInstance();
        operandValidator = mock(OperandValidator.class);
    }

    @Test
    void testCanReadValidOperationInput() throws IOException {
        String inputStr = "+";

        try (
             InputStream input = new ByteArrayInputStream(inputStr.getBytes())) {
            System.setIn(input);

            //When
            when(operandValidator.validate(inputStr)).thenReturn(true);
            String result = operationInputReader.getInput();

            //Then
            String expected = "+";
            assertEquals(expected, result);

        }
    }


    @Test
    void testRaiseExceptionForInValidOperandInput() throws IOException {
        String inputStr = "a";

        try (InputStream input = new ByteArrayInputStream(inputStr.getBytes())) {
            System.setIn(input);

            //When
            when(operandValidator.validate(inputStr)).thenReturn(false);

            //Then
            assertThrows(IllegalArgumentException.class, () -> operationInputReader.getInput());
        }
    }
}
