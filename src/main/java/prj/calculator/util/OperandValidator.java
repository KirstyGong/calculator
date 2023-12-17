package prj.calculator.util;

public class OperandValidator implements IValidator {

    private static final String OPERAND_VALIDATION_RULE = "\\d+(\\.\\d+)?";
    private static final int INPUT_SIZE_LIMIT = 9;


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

        if (input.length() > INPUT_SIZE_LIMIT) {
            return false;
        }

        return input.matches(OPERAND_VALIDATION_RULE);
    }
}
