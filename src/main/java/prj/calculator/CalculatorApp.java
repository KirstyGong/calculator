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

    private static CalculatorApp SINGLE_INSTANCE;

    private final InputReader operandInputReader;
    private final InputReader operationInputReader;
    private final Map<Operator, IArithmeticOperation> arithmeticOperations;

    private CalculatorApp(InputReader operandInputReader, InputReader operationInputReader, Map<Operator, IArithmeticOperation> arithmeticOperations) {
        this.operandInputReader = operandInputReader;
        this.operationInputReader = operationInputReader;
        this.arithmeticOperations = arithmeticOperations;
    }

    public static CalculatorApp getInstance(InputReader operandInputReader, InputReader operationInputReader, Map<Operator, IArithmeticOperation> arithmeticOperations) {
        if (SINGLE_INSTANCE == null) {
            SINGLE_INSTANCE = new CalculatorApp(operandInputReader, operationInputReader, arithmeticOperations);
        }

        return SINGLE_INSTANCE;
    }


    public double calculate() {

        final String firstNumber = operandInputReader.getInput();
        final String operator = operationInputReader.getInput();
        final String secondNumber = operandInputReader.getInput();

        final IArithmeticOperation arithmeticOperation = arithmeticOperations.get(Operator.getEnum(operator));

        return arithmeticOperation.apply(Double.parseDouble(firstNumber), Double.parseDouble(secondNumber));

    }

}
