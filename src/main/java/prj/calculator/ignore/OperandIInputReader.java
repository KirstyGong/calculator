package prj.calculator.ignore;

import prj.calculator.reader.IInputReader;
import prj.calculator.util.OperandValidator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class OperandIInputReader {
    private static OperandIInputReader SINGLE_INSTANCE;
    private final OperandValidator operandValidator;

    private OperandIInputReader() {
        operandValidator = OperandValidator.getInstance();
    }

    public static OperandIInputReader getInstance() {
        if (SINGLE_INSTANCE == null) {
            SINGLE_INSTANCE = new OperandIInputReader();
        }

        return SINGLE_INSTANCE;
    }

    public String getInput() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Input Operand:");

        try {
            String input = reader.readLine();
            if (!operandValidator.validate(input)) {
                throw new IllegalArgumentException("Invalid Operand");
            }
            return input;
        } catch (IOException | IllegalArgumentException exception) {
            throw new IllegalArgumentException(exception.getMessage());
        }
    }
}
