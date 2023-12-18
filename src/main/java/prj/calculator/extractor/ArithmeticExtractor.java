package prj.calculator.extractor;

import prj.calculator.model.CalculatorInput;
import prj.calculator.model.Operator;

import java.util.List;

public class ArithmeticExtractor implements IExtractor {


    private static ArithmeticExtractor SINGLE_INSTANCE;

    private ArithmeticExtractor() {

    }

    public static ArithmeticExtractor getInstance() {
        if (SINGLE_INSTANCE == null) {
            SINGLE_INSTANCE = new ArithmeticExtractor();
        }

        return SINGLE_INSTANCE;
    }

    public CalculatorInput extract(List<String> inputs) {

        final List<Double> operands = List.of(Double.parseDouble(inputs.get(0)), Double.parseDouble(inputs.get(2)));

        return new CalculatorInput(operands, Operator.getEnum(inputs.get(1)));

    }
}
