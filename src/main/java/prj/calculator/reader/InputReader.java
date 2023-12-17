package prj.calculator.reader;

import prj.calculator.util.IValidator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputReader implements IInputReader {

    private final IValidator validator;

    public InputReader(IValidator validator) {
        this.validator = validator;
    }

    public String getInput() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Input:");

        try {
            String input = reader.readLine();

            if (!validator.validate(input)) {
                throw new IllegalArgumentException("Invalid Operand");
            }
            return input;
        } catch (IOException | IllegalArgumentException exception) {
            throw new IllegalArgumentException(exception.getMessage());
        }
    }
}
