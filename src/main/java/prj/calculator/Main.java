package prj.calculator;

import prj.calculator.extractor.ArithmeticExtractor;
import prj.calculator.extractor.IExtractor;
import prj.calculator.model.Operator;
import prj.calculator.operation.*;
import prj.calculator.reader.InputReader;
import prj.calculator.util.IValidator;
import prj.calculator.util.InputValidator;

import java.util.Map;

public class Main {

    public static void main(String[] args) {

        final InputReader inputReader = InputReader.getInstance();
        final IValidator inputValidator = InputValidator.getInstance();
        final IExtractor extractor = ArithmeticExtractor.getInstance();
        final IOperationHandler operationHandler = AdditionOperation.getInstance(
                SubtractionOperation.getInstance(
                        MultiplicationOperation.getInstance(
                                DivisionOperation.getInstance(null)
                        )
                )
        );

        CalculatorApp calculatorApp = CalculatorApp.getInstance(inputReader, inputValidator, extractor, operationHandler);

        while (true) {
            try {
                System.out.println("\nEvery input either takes an operand or operation and maximum operand input size is 9.");
                System.out.println(String.format("Result: %f", calculatorApp.calculate()));
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

    }
}
