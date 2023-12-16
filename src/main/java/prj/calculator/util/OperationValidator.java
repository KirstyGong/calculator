package prj.calculator.util;

import java.util.Arrays;

import static prj.calculator.model.Operator.values;

public class OperationValidator implements IValidator {

    private static OperationValidator SINGLE_INSTANCE;

    private OperationValidator() {

    }

    public static OperationValidator getInstance() {
        if (SINGLE_INSTANCE == null) {
            SINGLE_INSTANCE = new OperationValidator();
        }

        return SINGLE_INSTANCE;
    }

    public boolean validate(String input) {

        if (input == null || input.isBlank()) {
            return false;
        }

        return Arrays.stream(values()).anyMatch(operator -> operator.value().equals(input));
    }
}
