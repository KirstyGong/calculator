package prj.calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import prj.calculator.extractor.ArithmeticExtractor;
import prj.calculator.extractor.IExtractor;
import prj.calculator.model.CalculatorInput;
import prj.calculator.model.Operator;
import prj.calculator.operation.AdditionOperation;
import prj.calculator.operation.IArithmeticOperation;
import prj.calculator.reader.InputReader;
import prj.calculator.util.IValidator;
import prj.calculator.util.InputValidator;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static prj.calculator.model.Operator.ADDITION_OPERATOR;

public class CalculatorAppTest {
    private InputReader mockedInputreader;
    private AdditionOperation mockedAdditionOperation;
    private CalculatorApp calculatorApp;
    private IExtractor mockedExtractor;

    @BeforeEach
    public void setup() {
        mockedInputreader = mock(InputReader.class);
        mockedAdditionOperation = mock(AdditionOperation.class);
        IValidator mockedInputValidator = mock(InputValidator.class);
        mockedExtractor = mock(ArithmeticExtractor.class);

        Map<Operator, IArithmeticOperation> arithmeticOperations = Map.of(ADDITION_OPERATOR, mockedAdditionOperation
        );

        calculatorApp = CalculatorApp.getInstance(mockedInputreader, mockedInputValidator, mockedExtractor, arithmeticOperations);

        when(mockedInputValidator.validate(anyString())).thenReturn(true).thenThrow(IllegalArgumentException.class);
    }

    @Test
    void testCanCalculate() {
        //Given
        final String firstOperand = "1";
        final String secondOperand = "2";
        final CalculatorInput extractedLCalculatorInput = new CalculatorInput(List.of(1.0, 2.0), ADDITION_OPERATOR);
        final double expected = 3.0;

        when(mockedInputreader.getInput()).thenReturn(List.of(firstOperand, ADDITION_OPERATOR.value(), secondOperand));
        when(mockedExtractor.extract(anyList())).thenReturn(extractedLCalculatorInput);
        when(mockedAdditionOperation.apply(extractedLCalculatorInput.getOperands())).thenReturn(expected);

        //When
        double result = calculatorApp.calculate();

        //Then
        assertEquals(expected, result);
    }


    @Test
    void testCanHandleInvalidInput() {

        //Then
        assertThrows(IllegalArgumentException.class, () -> calculatorApp.calculate());
    }

}
