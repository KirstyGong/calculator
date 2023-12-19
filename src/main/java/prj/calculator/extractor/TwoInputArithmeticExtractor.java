package prj.calculator.extractor;

import prj.calculator.model.CalculatorInput;
import prj.calculator.model.Operator;

import java.util.List;

public class TwoInputArithmeticExtractor implements IExtractor {


    private static TwoInputArithmeticExtractor SINGLE_INSTANCE;

    private TwoInputArithmeticExtractor() {

    }

    public static TwoInputArithmeticExtractor getInstance() {
        if (SINGLE_INSTANCE == null) {
            SINGLE_INSTANCE = new TwoInputArithmeticExtractor();
        }

        return SINGLE_INSTANCE;
    }

    public CalculatorInput extract(List<String> inputs) {

        final List<Double> operands = List.of(Double.parseDouble(inputs.get(0)), Double.parseDouble(inputs.get(2)));

        return new CalculatorInput(operands, Operator.getEnum(inputs.get(1)));

    }
}
