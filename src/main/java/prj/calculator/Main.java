package prj.calculator;

import prj.calculator.model.Operator;
import prj.calculator.operation.*;
import prj.calculator.reader.InputReader;
import prj.calculator.util.IValidator;
import prj.calculator.util.OperandValidator;
import prj.calculator.util.OperationValidator;

import java.util.Map;

import static prj.calculator.model.Operator.*;
import static prj.calculator.model.Operator.DIVISION_OPERATOR;

public class Main {

    public static void main(String[] args) {
        IValidator operandValidator = OperandValidator.getInstance();
        InputReader operandInputReader = new InputReader(operandValidator);

        IValidator operationValidator = OperationValidator.getInstance();
        InputReader operationInputReader = new InputReader(operationValidator);
        Map<Operator, IArithmeticOperation> arithmeticOperations = Map.of(ADDITION_OPERATOR, AdditionOperation.getInstance(),
                SUBTRACTION_OPERATOR, SubtractionOperation.getInstance(),
                MULTIPLICATION_OPERATOR, MultiplicationOperation.getInstance(),
                DIVISION_OPERATOR, DivisionOperation.getInstance()
        );

        CalculatorApp calculatorApp = CalculatorApp.getInstance(operandInputReader, operationInputReader, arithmeticOperations);

        while (true) {
            try {
                System.out.println("Every input either takes an operand or operation and maximum operand input size is 9.");
                System.out.println(calculatorApp.calculate());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                continue;
            }
        }

    }
}
