package prj.calculator.ignore;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.powermock.core.classloader.annotations.PrepareForTest;
import prj.calculator.CalculatorApp;
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
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;
import static prj.calculator.model.Operator.ADDITION_OPERATOR;
import static prj.calculator.model.Operator.DIVISION_OPERATOR;
import static prj.calculator.model.Operator.MULTIPLICATION_OPERATOR;
import static prj.calculator.model.Operator.SUBTRACTION_OPERATOR;

public class CalculatorAppTestIgnore {
    private static MockedStatic<AdditionOperation> mockedAdditionOperationStatic;
    private static MockedStatic<SubtractionOperation> mockedSubtractionOperationStatic;
    private static MockedStatic<DivisionOperation> mockedDivisionOperationStatic;
    private static MockedStatic<MultiplicationOperation> mockedMultiplicationStatic;

    private InputReader mockOperandReader;
    private InputReader mockOperationReader;
    private ByteArrayOutputStream byteArrayOutputStream;
    private IArithmeticOperation arithmeticOperation;

    @BeforeAll
    @PrepareForTest({AdditionOperation.class, SubtractionOperation.class, DivisionOperation.class, MultiplicationOperation.class})
    public static void setupBeforeAll() {
        mockedAdditionOperationStatic = mockStatic(AdditionOperation.class);
        mockedSubtractionOperationStatic = mockStatic(SubtractionOperation.class);
        mockedDivisionOperationStatic = mockStatic(DivisionOperation.class);
        mockedMultiplicationStatic = mockStatic(MultiplicationOperation.class);
    }

    @BeforeEach
    public void setup() {
        mockOperandReader = mock(InputReader.class);
        mockOperationReader = mock(InputReader.class);
        arithmeticOperation = mock(IArithmeticOperation.class);
        byteArrayOutputStream = new ByteArrayOutputStream();
    }

    @Test
    void testCanCalculateAddition() {
        //Given
        final String firstOperand = "1";
        final String secondOperand = "2";
        final double result = 3.0;

        final AdditionOperation additionOperation = mock(AdditionOperation.class);
        when(AdditionOperation.getInstance()).thenReturn(additionOperation);
        arithmeticOperation = additionOperation;

        when(mockOperandReader.getInput()).thenReturn(firstOperand, secondOperand);
        when(mockOperationReader.getInput()).thenReturn(ADDITION_OPERATOR.value());
        when(arithmeticOperation.apply(anyDouble(), anyDouble())).thenReturn(result);

        PrintStream out = new PrintStream(byteArrayOutputStream);
        System.setOut(out);

        //When
        CalculatorApp.calculate(mockOperandReader, mockOperationReader);

        //Then
        String consoleOutput = byteArrayOutputStream.toString(Charset.defaultCharset());
        assertTrue(consoleOutput.contains(String.format("Result: %f", result)));

        mockedAdditionOperationStatic.close();
    }

    @Test
    void testCanCalculateSubtraction() {
        //Given
        final String firstOperand = "2";
        final String secondOperand = "1";
        final double result = 1.0;

        final SubtractionOperation subtractionOperation = mock(SubtractionOperation.class);
        when(SubtractionOperation.getInstance()).thenReturn(subtractionOperation);
        arithmeticOperation = subtractionOperation;

        when(mockOperandReader.getInput()).thenReturn(firstOperand, secondOperand);
        when(mockOperationReader.getInput()).thenReturn(SUBTRACTION_OPERATOR.value());
        when(arithmeticOperation.apply(anyDouble(), anyDouble())).thenReturn(result);

        PrintStream out = new PrintStream(byteArrayOutputStream);
        System.setOut(out);

        //When
        CalculatorApp.calculate(mockOperandReader, mockOperationReader);

        //Then
        String consoleOutput = byteArrayOutputStream.toString(Charset.defaultCharset());
        assertTrue(consoleOutput.contains(String.format("Result: %f", result)));

        mockedSubtractionOperationStatic.close();
    }

    @Test
    void testCanCalculateMultiplication() {
        //Given
        final String firstOperand = "2";
        final String secondOperand = "1";
        final double result = 2.0;

        final MultiplicationOperation multiplicationOperation = mock(MultiplicationOperation.class);
        when(MultiplicationOperation.getInstance()).thenReturn(multiplicationOperation);
        arithmeticOperation = multiplicationOperation;

        when(mockOperandReader.getInput()).thenReturn(firstOperand, secondOperand);
        when(mockOperationReader.getInput()).thenReturn(MULTIPLICATION_OPERATOR.value());
        when(arithmeticOperation.apply(anyDouble(), anyDouble())).thenReturn(result);

        PrintStream out = new PrintStream(byteArrayOutputStream);
        System.setOut(out);

        //When
        CalculatorApp.calculate(mockOperandReader, mockOperationReader);

        //Then
        String consoleOutput = byteArrayOutputStream.toString(Charset.defaultCharset());
        assertTrue(consoleOutput.contains(String.format("Result: %f", result)));

        mockedMultiplicationStatic.close();
    }

    @Test
    void testCanCalculateDivision() {
        //Given
        final String firstOperand = "4";
        final String secondOperand = "2";
        final double result = 2.0;

        final DivisionOperation divisionOperation = mock(DivisionOperation.class);
        when(DivisionOperation.getInstance()).thenReturn(divisionOperation);
        arithmeticOperation = divisionOperation;

        when(mockOperandReader.getInput()).thenReturn(firstOperand, secondOperand);
        when(mockOperationReader.getInput()).thenReturn(DIVISION_OPERATOR.value());
        when(arithmeticOperation.apply(anyDouble(), anyDouble())).thenReturn(result);

        PrintStream out = new PrintStream(byteArrayOutputStream);
        System.setOut(out);

        //When
        CalculatorApp.calculate(mockOperandReader, mockOperationReader);

        //Then
        String consoleOutput = byteArrayOutputStream.toString(Charset.defaultCharset());
        assertTrue(consoleOutput.contains(String.format("Result: %f", result)));

        mockedDivisionOperationStatic.close();
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
