package prj.calculator.extractor;

import prj.calculator.model.CalculatorInput;
import prj.calculator.model.Operator;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArithmeticExtractor implements IExtractor{

    private static final Pattern VALIDATION_RULE = Pattern.compile("(\\d+(\\.\\d+)?)([+\\-*/])(\\d+(\\.\\d+)?)");

    private static ArithmeticExtractor SINGLE_INSTANCE;

    private ArithmeticExtractor() {

    }

    public static ArithmeticExtractor getInstance() {
        if (SINGLE_INSTANCE == null) {
            SINGLE_INSTANCE = new ArithmeticExtractor();
        }

        return SINGLE_INSTANCE;
    }

    public CalculatorInput extract(String inputString) {

        Matcher patterMatcher = VALIDATION_RULE.matcher(inputString);

        patterMatcher.find();
        final String firstNumber = patterMatcher.group(1);
        final String secondNumber = patterMatcher.group(4);

        final List<Double> operands = List.of(Double.parseDouble(firstNumber), Double.parseDouble(secondNumber));

        final String operator = patterMatcher.group(3);

        return new CalculatorInput(operands, Operator.getEnum(operator));

    }
}
