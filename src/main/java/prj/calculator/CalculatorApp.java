package prj.calculator;

import prj.calculator.model.Operator;
import prj.calculator.operation.IArithmeticOperation;
import prj.calculator.reader.InputReader;
import prj.calculator.util.IValidator;

import java.util.Map;

public class CalculatorApp {

    private static CalculatorApp SINGLE_INSTANCE;

    private final InputReader inputreader;
    private final Map<Operator, IArithmeticOperation> arithmeticOperations;
    private final IValidator inputValidator;

    private CalculatorApp(InputReader inputreader, Map<Operator, IArithmeticOperation> arithmeticOperations, IValidator inputValidator) {
        this.inputreader = inputreader;
        this.arithmeticOperations = arithmeticOperations;
        this.inputValidator = inputValidator;
    }

    public static CalculatorApp getInstance(InputReader inputreader, Map<Operator, IArithmeticOperation> arithmeticOperations, IValidator inputValidator) {
        if (SINGLE_INSTANCE == null) {
            SINGLE_INSTANCE = new CalculatorApp(inputreader, arithmeticOperations, inputValidator);
        }

        return SINGLE_INSTANCE;
    }


    public double calculate() {

        final String firstNumber = inputreader.getInput();
        final String operator = inputreader.getInput();
        final String secondNumber = inputreader.getInput();

        inputValidator.validate(String.format("%1$s%2$s%3$s", firstNumber, operator, secondNumber));

        final IArithmeticOperation arithmeticOperation = arithmeticOperations.get(Operator.getEnum(operator));

        return arithmeticOperation.apply(Double.parseDouble(firstNumber), Double.parseDouble(secondNumber));

    }

}
