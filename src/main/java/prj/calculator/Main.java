package prj.calculator;

import prj.calculator.extractor.IExtractor;
import prj.calculator.extractor.TwoInputArithmeticExtractor;
import prj.calculator.operation.AdditionOperation;
import prj.calculator.operation.DivisionOperation;
import prj.calculator.operation.IOperationHandler;
import prj.calculator.operation.MultiplicationOperation;
import prj.calculator.operation.SubtractionOperation;
import prj.calculator.reader.TwoInputArithmeticReader;
import prj.calculator.util.IValidator;
import prj.calculator.util.TwoInputArithmeticValidator;

public class Main {

    public static void main(String[] args) {

        final TwoInputArithmeticReader twoInputArithmeticReader = TwoInputArithmeticReader.getInstance();
        final IValidator inputValidator = TwoInputArithmeticValidator.getInstance();
        final IExtractor extractor = TwoInputArithmeticExtractor.getInstance();
        final IOperationHandler operationHandler = AdditionOperation.getInstance(
                SubtractionOperation.getInstance(
                        MultiplicationOperation.getInstance(
                                DivisionOperation.getInstance(null)
                        )
                )
        );

        CalculatorApp calculatorApp = CalculatorApp.getInstance(twoInputArithmeticReader, inputValidator, extractor, operationHandler);

        while (true) {
            try {
                System.out.println("\nEvery input either takes an operand or operation and maximum operand input size is 9.");
                System.out.printf("Result: %f%n", calculatorApp.calculate());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

    }
}
