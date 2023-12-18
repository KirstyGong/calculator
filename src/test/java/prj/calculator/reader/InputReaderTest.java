package prj.calculator.reader;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import prj.calculator.util.IValidator;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class InputReaderTest {
    private InputReader inputReader;

    @BeforeEach
    public void setup() {
        inputReader = InputReader.getInstance();
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "1",
            "+"
    })
    void testCanReadInput(String inputStr) throws IOException {
        //Given

        try (InputStream input = new ByteArrayInputStream(inputStr.getBytes())) {
            System.setIn(input);

            //When
            final String result = inputReader.getInput();

            //Then
            assertEquals(inputStr, result);
        }
    }


}
