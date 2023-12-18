package prj.calculator.extractor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import prj.calculator.model.CalculatorInput;
import prj.calculator.model.Operator;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static prj.calculator.model.Operator.ADDITION_OPERATOR;

public class ArithmeticExtractorTest {

    private ArithmeticExtractor arithmeticExtractor;

    @BeforeEach
    public void setup() {
        arithmeticExtractor = ArithmeticExtractor.getInstance();
    }

    @ParameterizedTest
    @CsvSource(value = {
            "1+2:+",
            "1-2:-",
            "1*2:*",
            "1/2:/",
    }, delimiter = ':')
    void canExtractWholeNumberCalculationInput(String inputString, String inputOperation) {

        // When
        CalculatorInput calculatorInput = arithmeticExtractor.extract(inputString);

        //Then
        assertEquals(List.of(1.0, 2.0), calculatorInput.getOperands());
        assertEquals(Operator.getEnum(inputOperation), calculatorInput.getOperator());
    }

    @ParameterizedTest
    @CsvSource(value = {
            "1.1+2.1:+"
    }, delimiter = ':')
    void canExtractDecimalInputCalculationInput(String inputString, String inputOperation) {

        // When
        CalculatorInput calculatorInput = arithmeticExtractor.extract(inputString);

        //Then
        assertEquals(List.of(1.1, 2.1), calculatorInput.getOperands());
        assertEquals(Operator.getEnum(inputOperation), calculatorInput.getOperator());
    }






}
