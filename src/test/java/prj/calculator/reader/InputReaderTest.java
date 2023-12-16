package prj.calculator.reader;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import prj.calculator.util.IValidator;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class InputReaderTest {
    private IValidator iValidator;
    private InputReader inputReader;

    @BeforeEach
    public void setup() {
        iValidator = mock(IValidator.class);
        inputReader = new InputReader(iValidator);
    }

    @Test
    void testCanReadValidInput() throws IOException {
        //Given
        final String inputStr = "someValidInput";

        try (InputStream input = new ByteArrayInputStream(inputStr.getBytes())) {
            System.setIn(input);

            when(iValidator.validate(inputStr)).thenReturn(true);

            //When
            final String result = inputReader.getInput();

            //Then
            assertEquals(inputStr, result);
        }
    }

    @Test
    void testRaiseExceptionForInValidInput() throws IOException {
        //Given
        final String inputStr = "someInValidInput";

        try (InputStream input = new ByteArrayInputStream(inputStr.getBytes())) {

            System.setIn(input);
            when(iValidator.validate(inputStr)).thenReturn(false);

            //Then
            assertThrows(IllegalArgumentException.class, inputReader::getInput);

        }

    }
}
