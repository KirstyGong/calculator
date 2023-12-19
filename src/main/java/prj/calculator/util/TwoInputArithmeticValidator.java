package prj.calculator.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TwoInputArithmeticValidator implements IValidator {
    private static final Pattern VALIDATION_RULE = Pattern.compile("(\\d+(\\.\\d+)?)([+\\-*/])(\\d+(\\.\\d+)?)");
    private static final int INPUT_SIZE_LIMIT = 9;

    private static TwoInputArithmeticValidator SINGLE_INSTANCE;

    private TwoInputArithmeticValidator() {

    }

    public static TwoInputArithmeticValidator getInstance() {
        if (SINGLE_INSTANCE == null) {
            SINGLE_INSTANCE = new TwoInputArithmeticValidator();
        }

        return SINGLE_INSTANCE;
    }

    public boolean validate(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(String.format("%s is an invalid input", input));
        }

        Matcher patterMatcher = VALIDATION_RULE.matcher(input);

        if (!patterMatcher.find()) {
            throw new IllegalArgumentException(String.format("%s is an invalid input", input));
        }

        if (patterMatcher.group(1).length() > INPUT_SIZE_LIMIT || patterMatcher.group(4).length() > INPUT_SIZE_LIMIT) {
            throw new IllegalArgumentException("Operand Size Exceed");
        }

        return true;
    }
}
