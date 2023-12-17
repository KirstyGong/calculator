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

import java.util.Map;

import static prj.calculator.model.Operator.ADDITION_OPERATOR;
import static prj.calculator.model.Operator.DIVISION_OPERATOR;
import static prj.calculator.model.Operator.MULTIPLICATION_OPERATOR;
import static prj.calculator.model.Operator.SUBTRACTION_OPERATOR;

public class CalculatorApp {


    public static void main(String[] args) {
        IValidator operandValidator = OperandValidator.getInstance();
        InputReader operandInputReader = new InputReader(operandValidator);

        IValidator operationValidator = OperationValidator.getInstance();
        InputReader operationInputReader = new InputReader(operationValidator);
        Map<Operator, IArithmeticOperation>  arithmeticOperations = Map.of(ADDITION_OPERATOR, AdditionOperation.getInstance(),
                SUBTRACTION_OPERATOR, SubtractionOperation.getInstance(),
                MULTIPLICATION_OPERATOR, MultiplicationOperation.getInstance(),
                DIVISION_OPERATOR, DivisionOperation.getInstance()
        );

        while (true) {
            try {
                System.out.println("Every input either takes an operand or operation and maximum operand input size is 9.");
                calculate(operandInputReader, operationInputReader, arithmeticOperations);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                continue;
            }
        }

    }

    public static void calculate(InputReader operandInputReader, InputReader operationInputReader, Map<Operator, IArithmeticOperation> arithmeticOperations) {

        final String firstNumber = operandInputReader.getInput();
        final String operator = operationInputReader.getInput();
        final String secondNumber = operandInputReader.getInput();

        final IArithmeticOperation arithmeticOperation = arithmeticOperations.get(Operator.getEnum(operator));

        double result = arithmeticOperation.apply(Double.parseDouble(firstNumber), Double.parseDouble(secondNumber));

        System.out.printf("Result: %f%n", result);
    }

}
