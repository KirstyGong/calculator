package prj.calculator.ignore;

import prj.calculator.reader.IInputReader;
import prj.calculator.util.OperationValidator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class OperationIInputReader {

    private static OperationIInputReader SINGLE_INSTANCE;
    private final OperationValidator operationValidator;

    private OperationIInputReader() {
        operationValidator = OperationValidator.getInstance();
    }

    public static OperationIInputReader getInstance() {
        if (SINGLE_INSTANCE == null) {
            SINGLE_INSTANCE = new OperationIInputReader();
        }

        return SINGLE_INSTANCE;
    }

    public String getInput() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Input Operation:");

        try {
            String input = reader.readLine();

            if (!operationValidator.validate(input)) {
                throw new IllegalArgumentException("Invalid Operand");
            }
            return input;
        } catch (IOException | IllegalArgumentException exception) {
            throw new IllegalArgumentException(exception.getMessage());
        }
    }
}
