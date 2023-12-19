package prj.calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import prj.calculator.extractor.IExtractor;
import prj.calculator.extractor.TwoInputArithmeticExtractor;
import prj.calculator.operation.AdditionOperation;
import prj.calculator.operation.DivisionOperation;
import prj.calculator.operation.IOperationHandler;
import prj.calculator.operation.MultiplicationOperation;
import prj.calculator.operation.SubtractionOperation;
import prj.calculator.reader.TwoInputArithmeticReader;
import prj.calculator.util.IValidator;
import prj.calculator.util.TwoInputArithmeticValidator;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CalculatorAppAcceptanceTest {

    private final IValidator inputValidator = TwoInputArithmeticValidator.getInstance();
    private final IExtractor extractor = TwoInputArithmeticExtractor.getInstance();
    private final IOperationHandler operationHandler = AdditionOperation.getInstance(
            SubtractionOperation.getInstance(
                    MultiplicationOperation.getInstance(
                            DivisionOperation.getInstance(null)
                    )
            )
    );
    private TwoInputArithmeticReader mockedInputReader;
    private CalculatorApp calculatorApp;

    @BeforeEach
    public void setup() {
        mockedInputReader = mock(TwoInputArithmeticReader.class);
        when(mockedInputReader.getInput()).thenReturn(
                List.of("1"),
                List.of("1", "+", "1"),
                List.of("1", "-", "1"),
                List.of("1", "*", "1"),
                List.of("1", "/", "1"),
                List.of("1", "/", "0")
        );
        calculatorApp = CalculatorApp.getInstance(mockedInputReader, inputValidator, extractor, operationHandler);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "2.0",
            "0.0",
            "1.0",
            "1.0"
    })
    public void testCanComputeResultForValidInput(String expected) {
        when(mockedInputReader.getInput()).thenReturn(
                List.of("1", "+", "1"),
                List.of("1", "-", "1"),
                List.of("1", "*", "1"),
                List.of("1", "/", "1"),
                List.of("1", "/", "0")
        );
        // When
        final double result = Main.calculate(calculatorApp);
        assertEquals(expected, Double.toString(result));
    }

    @Test
    public void testShouldRaiseExceptionForInvalidInput() {
        when(mockedInputReader.getInput()).thenReturn(
                List.of("1")
        );
        // When
        assertThrows(IllegalArgumentException.class, () -> Main.calculate(calculatorApp));
    }

    @Test
    public void testShouldRaiseExceptionForDividingByZero() {
        when(mockedInputReader.getInput()).thenReturn(
                List.of("1/0")
        );
        // When
        assertThrows(IllegalArgumentException.class, () -> Main.calculate(calculatorApp));
    }
}
