package prj.calculator.ignore;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import prj.calculator.ignore.OperandIInputReader;
import prj.calculator.util.OperandValidator;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OperandInputReaderTest {

    private OperandIInputReader operandInputReader;
    private OperandValidator operandValidator;

    @BeforeEach
    public void setup() {
        operandInputReader = OperandIInputReader.getInstance();
        operandValidator = mock(OperandValidator.class);
    }

    @Test
    void testCanReadValidOperandInput() throws IOException {
        String inputStr = "1";

        try (
             InputStream input = new ByteArrayInputStream(inputStr.getBytes())) {
            //Given
            System.setIn(input);
            when(operandValidator.validate(inputStr)).thenReturn(true);


            //When
            String result = operandInputReader.getInput();

            //Then
            String expected = "1";
            assertEquals(expected, result);

        }
    }

    @Test
    void testCanReadValidDecimalOperandInput() throws IOException {
        String inputStr = "1.1";

        try (InputStream input = new ByteArrayInputStream(inputStr.getBytes())) {
            //Given
            System.setIn(input);
            when(operandValidator.validate(inputStr)).thenReturn(true);

            //When
            String result = operandInputReader.getInput();

            //Then
            String expected = "1.1";
            assertEquals(expected, result);

        }
    }

    @Test
    void testRaiseExceptionForInValidOperandInput() throws IOException {
        String inputStr = "a";

        try (InputStream input = new ByteArrayInputStream(inputStr.getBytes())) {
            System.setIn(input);

            //Given
            when(operandValidator.validate(inputStr)).thenReturn(false);

            //Then
            assertThrows(IllegalArgumentException.class, () -> operandInputReader.getInput());


        }
    }
}
