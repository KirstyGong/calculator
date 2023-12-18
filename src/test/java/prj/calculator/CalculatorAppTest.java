package prj.calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import prj.calculator.model.Operator;
import prj.calculator.operation.AdditionOperation;
import prj.calculator.operation.IArithmeticOperation;
import prj.calculator.reader.InputReader;
import prj.calculator.util.IValidator;
import prj.calculator.util.InputValidator;

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
    private IValidator mockedInputValidator;

    @BeforeEach
    public void setup() {
        mockedInputreader = mock(InputReader.class);
        mockedAdditionOperation = mock(AdditionOperation.class);
        mockedInputValidator = mock(InputValidator.class);

        Map<Operator, IArithmeticOperation> arithmeticOperations = Map.of(ADDITION_OPERATOR, mockedAdditionOperation
        );

        calculatorApp = CalculatorApp.getInstance(mockedInputreader, arithmeticOperations, mockedInputValidator);
    }

    @Test
    void testCanCalculate() {
        //Given
        final String firstOperand = "1";
        final String secondOperand = "2";
        final double expected = 3.0;

        when(mockedInputreader.getInput()).thenReturn(firstOperand, ADDITION_OPERATOR.value(), secondOperand);
        when(mockedAdditionOperation.apply(anyDouble(), anyDouble())).thenReturn(expected);
        when(mockedInputValidator.validate(anyString())).thenReturn(true);

        //When
        double result = calculatorApp.calculate();

        //Then
        assertEquals(expected, result);
    }


    @Test
    void testCanHandleInvalidInput() {

        //When
        when(mockedInputValidator.validate(any())).thenThrow(IllegalArgumentException.class);

        //Then
        assertThrows(IllegalArgumentException.class, () -> calculatorApp.calculate());
    }

}
