package prj.calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import prj.calculator.extractor.IExtractor;
import prj.calculator.extractor.TwoInputArithmeticExtractor;
import prj.calculator.model.CalculatorInput;
import prj.calculator.operation.AdditionOperation;
import prj.calculator.operation.IOperationHandler;
import prj.calculator.reader.TwoInputArithmeticReader;
import prj.calculator.util.IValidator;
import prj.calculator.util.TwoInputArithmeticValidator;

import java.util.List;
import java.util.concurrent.locks.StampedLock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static prj.calculator.model.Operator.ADDITION_OPERATOR;

public class CalculatorAppTest {
    private TwoInputArithmeticReader mockedInputReader;
    private CalculatorApp calculatorApp;
    private IExtractor mockedExtractor;
    private IOperationHandler operationHandler;
    private TwoInputArithmeticValidator mockedInputValidator;

    @BeforeEach
    public void setup() {
        mockedInputReader = mock(TwoInputArithmeticReader.class);
        mockedInputValidator = mock(TwoInputArithmeticValidator.class);
        mockedExtractor = mock(TwoInputArithmeticExtractor.class);
        operationHandler = mock(AdditionOperation.class);

        calculatorApp = CalculatorApp.getInstance(mockedInputReader, mockedInputValidator, mockedExtractor, operationHandler);

        when(mockedInputValidator.validate(anyString()))
                .thenReturn(true)
                .thenThrow(IllegalArgumentException.class);
    }

    @Test
    void testCanCalculate() {
        //Given
        final String firstOperand = "1";
        final String secondOperand = "2";
        final CalculatorInput extractedLCalculatorInput = new CalculatorInput(List.of(1.0, 2.0), ADDITION_OPERATOR);
        final double expected = 3.0;

        when(mockedInputReader.getInput()).thenReturn(List.of(firstOperand, ADDITION_OPERATOR.value(), secondOperand));
        when(mockedExtractor.extract(anyList())).thenReturn(extractedLCalculatorInput);
        when(operationHandler.handle(
                extractedLCalculatorInput.getOperator(),
                extractedLCalculatorInput.getOperands())
        ).thenReturn(expected);

        //When
        double result = calculatorApp.calculate();

        //Then
        assertEquals(expected, result);
    }

    @Test
    void testRaiseExceptionForInvalidInput() {
        when(mockedInputValidator.validate(anyString()))
                .thenThrow(IllegalArgumentException.class);
        assertThrows(IllegalArgumentException.class, () -> calculatorApp.calculate());
    }

}
