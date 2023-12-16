package prj.calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import prj.calculator.operation.AdditionOperation;
import prj.calculator.operation.DivisionOperation;
import prj.calculator.operation.IArithmeticOperation;
import prj.calculator.operation.MultiplicationOperation;
import prj.calculator.operation.SubtractionOperation;
import prj.calculator.reader.InputReader;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.Charset;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
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

    @BeforeEach
    public void setup() {
        mockOperandReader = mock(InputReader.class);
        mockOperationReader = mock(InputReader.class);
        byteArrayOutputStream = new ByteArrayOutputStream();
    }

    @Test
    void testCanCalculateAddition() {
        //Given
        final String firstOperand = "1";
        final String secondOperand = "2";
        final double result = 3.0;

        when(mockOperandReader.getInput()).thenReturn(firstOperand, secondOperand);
        when(mockOperationReader.getInput()).thenReturn(ADDITION_OPERATOR.value());

        PrintStream out = new PrintStream(byteArrayOutputStream);
        System.setOut(out);

        //When
        CalculatorApp.calculate(mockOperandReader, mockOperationReader);

        //Then
        String consoleOutput = byteArrayOutputStream.toString(Charset.defaultCharset());
        assertTrue(consoleOutput.contains(String.format("Result: %f", result)));

    }

    @Test
    void testCanCalculateSubtraction() {
        //Given
        final String firstOperand = "2";
        final String secondOperand = "1";
        final double result = 1.0;

        when(mockOperandReader.getInput()).thenReturn(firstOperand, secondOperand);
        when(mockOperationReader.getInput()).thenReturn(SUBTRACTION_OPERATOR.value());

        PrintStream out = new PrintStream(byteArrayOutputStream);
        System.setOut(out);

        //When
        CalculatorApp.calculate(mockOperandReader, mockOperationReader);

        //Then
        String consoleOutput = byteArrayOutputStream.toString(Charset.defaultCharset());
        assertTrue(consoleOutput.contains(String.format("Result: %f", result)));

    }

    @Test
    void testCanCalculateMultiplication() {
        //Given
        final String firstOperand = "2";
        final String secondOperand = "1";
        final double result = 2.0;

        when(mockOperandReader.getInput()).thenReturn(firstOperand, secondOperand);
        when(mockOperationReader.getInput()).thenReturn(MULTIPLICATION_OPERATOR.value());

        PrintStream out = new PrintStream(byteArrayOutputStream);
        System.setOut(out);

        //When
        CalculatorApp.calculate(mockOperandReader, mockOperationReader);

        //Then
        String consoleOutput = byteArrayOutputStream.toString(Charset.defaultCharset());
        assertTrue(consoleOutput.contains(String.format("Result: %f", result)));

    }

    @Test
    void testCanCalculateDivision() {
        //Given
        final String firstOperand = "4";
        final String secondOperand = "2";
        final double result = 2.0;

        when(mockOperandReader.getInput()).thenReturn(firstOperand, secondOperand);
        when(mockOperationReader.getInput()).thenReturn(DIVISION_OPERATOR.value());

        PrintStream out = new PrintStream(byteArrayOutputStream);
        System.setOut(out);

        //When
        CalculatorApp.calculate(mockOperandReader, mockOperationReader);

        //Then
        String consoleOutput = byteArrayOutputStream.toString(Charset.defaultCharset());
        assertTrue(consoleOutput.contains(String.format("Result: %f", result)));

    }

    @Test
    void testCanHandleInvalidFirstInput() {
        //Given
        final String firstOperand = "a";

        when(mockOperandReader.getInput()).thenReturn(firstOperand);

        PrintStream out = new PrintStream(byteArrayOutputStream);
        System.setOut(out);

        //Then
        assertThrows(IllegalArgumentException.class, () -> CalculatorApp.calculate(mockOperandReader, mockOperationReader));
    }

    @Test
    void testCanHandleInvalidSecondInput() {
        //Given
        final String firstOperand = "1";
        final String operation = "plus";

        when(mockOperandReader.getInput()).thenReturn(firstOperand);
        when(mockOperationReader.getInput()).thenReturn(operation);

        PrintStream out = new PrintStream(byteArrayOutputStream);
        System.setOut(out);

        //Then
        assertThrows(IllegalArgumentException.class, () -> CalculatorApp.calculate(mockOperandReader, mockOperationReader));
    }

    @Test
    void testCanHandleInvalidLastInput() {
        //Given
        final String firstOperand = "1";
        final String operation = "+";
        final String secondOperand = "a";

        when(mockOperandReader.getInput()).thenReturn(firstOperand, secondOperand);
        when(mockOperationReader.getInput()).thenReturn(operation);

        PrintStream out = new PrintStream(byteArrayOutputStream);
        System.setOut(out);

        //Then
        assertThrows(IllegalArgumentException.class, () -> CalculatorApp.calculate(mockOperandReader, mockOperationReader));
    }
}
