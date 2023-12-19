package prj.calculator.reader;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TwoInputArithmeticReaderTest {
    private TwoInputArithmeticReader twoInputArithmeticReader;

    @BeforeEach
    public void setup() {
        twoInputArithmeticReader = TwoInputArithmeticReader.getInstance();
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "1",
            "+"
    })
    void testCanReadInput(String inputStr) throws IOException {
        //Given
        final String formatInputStr = String.format("%1$s\n%1$s\n%1$s", inputStr);
        try (InputStream input = new ByteArrayInputStream(formatInputStr.getBytes())) {
            System.setIn(input);

            //When
            final List<String> result = twoInputArithmeticReader.getInput();

            //Then
            final List<String> expected = List.of(inputStr, inputStr, inputStr);
            assertEquals(expected, result);
        }
    }


}