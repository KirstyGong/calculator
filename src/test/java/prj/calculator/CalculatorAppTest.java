package prj.calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import prj.calculator.model.Operator;
import prj.calculator.operation.AdditionOperation;
import prj.calculator.operation.DivisionOperation;
import prj.calculator.operation.IArithmeticOperation;
import prj.calculator.operation.MultiplicationOperation;
import prj.calculator.operation.SubtractionOperation;
import prj.calculator.reader.InputReader;

import java.io.ByteArrayOutputStream;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static prj.calculator.model.Operator.ADDITION_OPERATOR;
import static prj.calculator.model.Operator.DIVISION_OPERATOR;
import static prj.calculator.model.Operator.MULTIPLICATION_OPERATOR;
import static prj.calculator.model.Operator.SUBTRACTION_OPERATOR;

public class CalculatorAppTest {
    private InputReader mockOperandReader;
    private InputReader mockOperationReader;
    private ByteArrayOutputStream byteArrayOutputStream;
    private AdditionOperation mockedAdditionOperation;
    private SubtractionOperation mockedSubtractionOperation;
    private MultiplicationOperation mockedMultiplicationOperation;
    private DivisionOperation mockedDivisionOperation;
    private CalculatorApp calculatorApp;


    @BeforeEach
    public void setup() {
        mockOperandReader = mock(InputReader.class);
        mockOperationReader = mock(InputReader.class);
        mockedAdditionOperation = mock(AdditionOperation.class);
        mockedSubtractionOperation = mock(SubtractionOperation.class);
        mockedMultiplicationOperation = mock(MultiplicationOperation.class);
        mockedDivisionOperation = mock(DivisionOperation.class);


        byteArrayOutputStream = new ByteArrayOutputStream();
        Map<Operator, IArithmeticOperation> arithmeticOperations = Map.of(ADDITION_OPERATOR, mockedAdditionOperation,
                SUBTRACTION_OPERATOR, mockedSubtractionOperation,
                MULTIPLICATION_OPERATOR, mockedMultiplicationOperation,
                DIVISION_OPERATOR, mockedDivisionOperation
        );

        calculatorApp = CalculatorApp.getInstance(mockOperandReader, mockOperationReader, arithmeticOperations);
    }

    @Test
    void testCanCalculate() {
        //Given
        final String firstOperand = "1";
        final String secondOperand = "2";
        final double expected = 3.0;

        when(mockOperandReader.getInput()).thenReturn(firstOperand, secondOperand);
        when(mockOperationReader.getInput()).thenReturn(ADDITION_OPERATOR.value());
        when(mockedAdditionOperation.apply(anyDouble(), anyDouble())).thenReturn(expected);

        //When
        double result = calculatorApp.calculate();

        //Then
        assertEquals(expected, result);
    }


    @Test
    void testCanHandleInvalidInput() {

        //When
        when(mockOperandReader.getInput()).thenThrow(IllegalArgumentException.class);

        //Then
        assertThrows(IllegalArgumentException.class, () -> calculatorApp.calculate());
    }

}
