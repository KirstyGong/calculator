package prj.calculator.extractor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import prj.calculator.model.CalculatorInput;
import prj.calculator.model.Operator;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TwoInputArithmeticExtractorTest {

    private TwoInputArithmeticExtractor twoInputArithmeticExtractor;

    @BeforeEach
    public void setup() {
        twoInputArithmeticExtractor = TwoInputArithmeticExtractor.getInstance();
    }

    @Test
    void canExtractAdditionCalculationInput() {

        final List<String> inputString = List.of("1.0", "+", "2.0");

        // When
        CalculatorInput calculatorInput = twoInputArithmeticExtractor.extract(inputString);

        //Then
        assertEquals(List.of(1.0, 2.0), calculatorInput.getOperands());
        assertEquals(Operator.getEnum(inputString.get(1)), calculatorInput.getOperator());
    }

    @Test
    void canExtractSubtractionCalculationInput() {

        final List<String> inputString = List.of("1.0", "-", "2.0");

        // When
        CalculatorInput calculatorInput = twoInputArithmeticExtractor.extract(inputString);

        //Then
        assertEquals(List.of(1.0, 2.0), calculatorInput.getOperands());
        assertEquals(Operator.getEnum(inputString.get(1)), calculatorInput.getOperator());
    }

    @Test
    void canExtractMultiplicationCalculationInput() {

        final List<String> inputString = List.of("1.0", "*", "2.0");

        // When
        CalculatorInput calculatorInput = twoInputArithmeticExtractor.extract(inputString);

        //Then
        assertEquals(List.of(1.0, 2.0), calculatorInput.getOperands());
        assertEquals(Operator.getEnum(inputString.get(1)), calculatorInput.getOperator());
    }

    @Test
    void canExtractDivisionCalculationInput() {

        final List<String> inputString = List.of("1.0", "/", "2.0");

        // When
        CalculatorInput calculatorInput = twoInputArithmeticExtractor.extract(inputString);

        //Then
        assertEquals(List.of(1.0, 2.0), calculatorInput.getOperands());
        assertEquals(Operator.getEnum(inputString.get(1)), calculatorInput.getOperator());
    }

    @Test
    void canExtractDecimalInputCalculationInput() {

        // Given
        final List<String> inputString = List.of("1.1", "+", "2.2");

        // When
        CalculatorInput calculatorInput = twoInputArithmeticExtractor.extract(inputString);

        //Then
        assertEquals(List.of(1.1, 2.2), calculatorInput.getOperands());
        assertEquals(Operator.getEnum(inputString.get(1)), calculatorInput.getOperator());
    }


}
