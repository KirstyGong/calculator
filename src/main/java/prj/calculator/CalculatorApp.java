package prj.calculator;

import prj.calculator.model.Operator;
import prj.calculator.operation.AdditionOperation;
import prj.calculator.operation.DivisionOperation;
import prj.calculator.operation.IArithmeticOperation;
import prj.calculator.operation.MultiplicationOperation;
import prj.calculator.operation.SubtractionOperation;
import prj.calculator.reader.InputReader;
import prj.calculator.util.IValidator;
import prj.calculator.util.OperandValidator;
import prj.calculator.util.OperationValidator;

public class CalculatorApp {


    public static void main(String[] args) {
        IValidator operandValidator = OperandValidator.getInstance();
        InputReader operandInputReader = new InputReader(operandValidator);

        IValidator operationValidator = OperationValidator.getInstance();
        InputReader operationInputReader = new InputReader(operationValidator);

        while (true) {
            try {
                calculate(operandInputReader, operationInputReader);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                continue;
            }
        }

    }

    public static void calculate(InputReader operandInputReader, InputReader operationInputReader) {
        IArithmeticOperation arithmeticOperation = null;

        final String firstNumber = operandInputReader.getInput();
        final String operator = operationInputReader.getInput();
        final String secondNumber = operandInputReader.getInput();

        switch (Operator.getEnum(operator)) {
            case ADDITION_OPERATOR:
                arithmeticOperation = AdditionOperation.getInstance();
                break;
            case SUBTRACTION_OPERATOR:
                arithmeticOperation = SubtractionOperation.getInstance();
                break;
            case MULTIPLICATION_OPERATOR:
                arithmeticOperation = MultiplicationOperation.getInstance();
                break;
            case DIVISION_OPERATOR:
                arithmeticOperation = DivisionOperation.getInstance();
                break;
            default:
                throw new IllegalArgumentException("Invalid input");
        }

        double result = arithmeticOperation.apply(Double.parseDouble(firstNumber), Double.parseDouble(secondNumber));

        System.out.printf("Result: %f%n", result);
    }

}
