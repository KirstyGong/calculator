package prj.calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import prj.calculator.CalculatorApp;
import prj.calculator.model.Operator;
import prj.calculator.operation.AdditionOperation;
import prj.calculator.operation.DivisionOperation;
import prj.calculator.operation.IArithmeticOperation;
import prj.calculator.operation.MultiplicationOperation;
import prj.calculator.operation.SubtractionOperation;
import prj.calculator.reader.InputReader;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
    private Map<Operator, IArithmeticOperation> arithmeticOperations;
    private AdditionOperation mockedAdditionOperation;
    private SubtractionOperation mockedSubtractionOperation;
    private MultiplicationOperation mockedMultiplicationOperation;
    private DivisionOperation mockedDivisionOperation;

    @BeforeEach
    public void setup() {
        mockOperandReader = mock(InputReader.class);
        mockOperationReader = mock(InputReader.class);
        mockedAdditionOperation = mock(AdditionOperation.class);
        mockedSubtractionOperation = mock(SubtractionOperation.class);
        mockedMultiplicationOperation = mock(MultiplicationOperation.class);
        mockedDivisionOperation = mock(DivisionOperation.class);


        byteArrayOutputStream = new ByteArrayOutputStream();
        arithmeticOperations = Map.of(ADDITION_OPERATOR, mockedAdditionOperation,
                SUBTRACTION_OPERATOR, mockedSubtractionOperation,
                MULTIPLICATION_OPERATOR, mockedMultiplicationOperation,
                DIVISION_OPERATOR, mockedDivisionOperation
        );
    }

    @Test
    void testCanCalculateAddition() {
        //Given
        final String firstOperand = "1";
        final String secondOperand = "2";
        final double result = 3.0;

        when(mockOperandReader.getInput()).thenReturn(firstOperand, secondOperand);
        when(mockOperationReader.getInput()).thenReturn(ADDITION_OPERATOR.value());
        when(mockedAdditionOperation.apply(anyDouble(), anyDouble())).thenReturn(result);

        final PrintStream out = new PrintStream(byteArrayOutputStream);
        System.setOut(out);

        //When
        CalculatorApp.calculate(mockOperandReader, mockOperationReader, arithmeticOperations);

        //Then
        String consoleOutput = byteArrayOutputStream.toString(Charset.defaultCharset());
        assertTrue(consoleOutput.contains(String.format("Result: %f", result)));
    }

    @Test
    void testCanCalculateSubtraction() {
        //Given
        final String firstOperand = "4";
        final String secondOperand = "2";
        final double result = 2.0;

        when(mockOperandReader.getInput()).thenReturn(firstOperand, secondOperand);
        when(mockOperationReader.getInput()).thenReturn(SUBTRACTION_OPERATOR.value());
        when(mockedSubtractionOperation.apply(anyDouble(), anyDouble())).thenReturn(result);

        final PrintStream out = new PrintStream(byteArrayOutputStream);
        System.setOut(out);

        //When
        CalculatorApp.calculate(mockOperandReader, mockOperationReader, arithmeticOperations);

        //Then
        String consoleOutput = byteArrayOutputStream.toString(Charset.defaultCharset());
        assertTrue(consoleOutput.contains(String.format("Result: %f", result)));
    }

    @Test
    void testCanCalculateMultiplication() {
        //Given
        final String firstOperand = "4";
        final String secondOperand = "2";
        final double result = 8.0;

        when(mockOperandReader.getInput()).thenReturn(firstOperand, secondOperand);
        when(mockOperationReader.getInput()).thenReturn(MULTIPLICATION_OPERATOR.value());
        when(mockedMultiplicationOperation.apply(anyDouble(), anyDouble())).thenReturn(result);

        final PrintStream out = new PrintStream(byteArrayOutputStream);
        System.setOut(out);

        //When
        CalculatorApp.calculate(mockOperandReader, mockOperationReader, arithmeticOperations);

        //Then
        String consoleOutput = byteArrayOutputStream.toString(Charset.defaultCharset());
        assertTrue(consoleOutput.contains(String.format("Result: %f", result)));
    }

    @Test
    void testCanCalculateDivision() {
        //Given
        final String firstOperand = "6";
        final String secondOperand = "2";
        final double result = 3.0;

        when(mockOperandReader.getInput()).thenReturn(firstOperand, secondOperand);
        when(mockOperationReader.getInput()).thenReturn(DIVISION_OPERATOR.value());
        when(mockedDivisionOperation.apply(anyDouble(), anyDouble())).thenReturn(result);

        final PrintStream out = new PrintStream(byteArrayOutputStream);
        System.setOut(out);

        //When
        CalculatorApp.calculate(mockOperandReader, mockOperationReader, arithmeticOperations);

        //Then
        String consoleOutput = byteArrayOutputStream.toString(Charset.defaultCharset());
        assertTrue(consoleOutput.contains(String.format("Result: %f", result)));
    }

        @Test
    void testCanHandleInvalidInput() {
        //Given
        final String firstOperand = "a";

        when(mockOperandReader.getInput()).thenReturn(firstOperand);

        final PrintStream out = new PrintStream(byteArrayOutputStream);
        System.setOut(out);

        //Then
        assertThrows(IllegalArgumentException.class, () -> CalculatorApp.calculate(mockOperandReader, mockOperationReader, arithmeticOperations));
    }

}
