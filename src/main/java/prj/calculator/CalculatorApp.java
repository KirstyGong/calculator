package prj.calculator;

import prj.calculator.extractor.IExtractor;
import prj.calculator.model.CalculatorInput;
import prj.calculator.model.Operator;
import prj.calculator.operation.IArithmeticOperation;
import prj.calculator.reader.IInputReader;
import prj.calculator.util.IValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CalculatorApp {

    private static CalculatorApp SINGLE_INSTANCE;

    private final IInputReader inputReader;
    private final Map<Operator, IArithmeticOperation> arithmeticOperations;
    private final IValidator inputValidator;
    private final IExtractor extractor;

    private CalculatorApp(
            IInputReader inputreader,
            IValidator validator,
            IExtractor extractor,
            Map<Operator, IArithmeticOperation> arithmeticOperations
    ) {
        this.inputReader = inputreader;
        this.inputValidator = validator;
        this.extractor = extractor;
        this.arithmeticOperations = arithmeticOperations;
    }

    public static CalculatorApp getInstance(
            IInputReader inputReader,
            IValidator validator,
            IExtractor extractor,
            Map<Operator, IArithmeticOperation> arithmeticOperations
    ) {
        if (SINGLE_INSTANCE == null) {
            SINGLE_INSTANCE = new CalculatorApp(inputReader, validator, extractor, arithmeticOperations);
        }

        return SINGLE_INSTANCE;
    }


    public double calculate() {

        final List<String> input = inputReader.getInput();

        inputValidator.validate(String.join("", input));

        CalculatorInput calculatorInput= extractor.extract(input);

        final IArithmeticOperation arithmeticOperation = arithmeticOperations.get(calculatorInput.getOperator());

        return arithmeticOperation.apply(calculatorInput.getOperands());

    }

}
