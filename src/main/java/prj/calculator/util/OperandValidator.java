package prj.calculator.util;

public class OperandValidator implements IValidator {
    private static OperandValidator SINGLE_INSTANCE;

    private OperandValidator() {

    }

    public static OperandValidator getInstance() {
        if (SINGLE_INSTANCE == null) {
            SINGLE_INSTANCE = new OperandValidator();
        }

        return SINGLE_INSTANCE;
    }

    public boolean validate(String input) {

        if (input == null || input.isBlank()) {
            return false;
        }

        return input.matches("\\d+(\\.\\d+)?");
    }
}
