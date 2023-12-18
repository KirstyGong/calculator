package prj.calculator.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidator implements IValidator{
    private static final Pattern VALIDATION_RULE = Pattern.compile("\\d+(\\.\\d+)?[+\\-*/]\\d+(\\.\\d+)?");
    private static final int INPUT_SIZE_LIMIT = 9;

    private static InputValidator SINGLE_INSTANCE;

    private InputValidator() {

    }

    public static InputValidator getInstance() {
        if (SINGLE_INSTANCE == null) {
            SINGLE_INSTANCE = new InputValidator();
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

        if (patterMatcher.group(0).length() > INPUT_SIZE_LIMIT || patterMatcher.group(2).length() > INPUT_SIZE_LIMIT) {
            throw new IllegalArgumentException("Operand Size Exceed");
        }

        return true;
    }
}
