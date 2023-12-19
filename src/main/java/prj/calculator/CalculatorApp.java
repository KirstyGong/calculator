package prj.calculator;

import prj.calculator.extractor.IExtractor;
import prj.calculator.model.CalculatorInput;
import prj.calculator.operation.IOperationHandler;
import prj.calculator.reader.IInputReader;
import prj.calculator.util.IValidator;

import java.util.List;

public class CalculatorApp {

    private static final String JOINING_STRING = "";

    private static CalculatorApp SINGLE_INSTANCE;

    private final IInputReader inputReader;
    private final IValidator inputValidator;
    private final IExtractor extractor;
    private final IOperationHandler operationHandler;

    private CalculatorApp(
            IInputReader inputReader,
            IValidator validator,
            IExtractor extractor,
            IOperationHandler operationHandler) {
        this.inputReader = inputReader;
        this.inputValidator = validator;
        this.extractor = extractor;
        this.operationHandler = operationHandler;
    }

    public static CalculatorApp getInstance(
            IInputReader inputReader,
            IValidator validator,
            IExtractor extractor,
            IOperationHandler operationHandler
    ) {
        if (SINGLE_INSTANCE == null) {
            SINGLE_INSTANCE = new CalculatorApp(inputReader, validator, extractor, operationHandler);
        }

        return SINGLE_INSTANCE;
    }


    public double calculate() {

        final List<String> input = inputReader.getInput();

        inputValidator.validate(String.join(JOINING_STRING, input));

        CalculatorInput calculatorInput = extractor.extract(input);

        return operationHandler.handle(calculatorInput.getOperator(), calculatorInput.getOperands());
    }

}
