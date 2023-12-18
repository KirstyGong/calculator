package prj.calculator.reader;

import prj.calculator.operation.AdditionOperation;
import prj.calculator.util.IValidator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputReader implements IInputReader {

    private static InputReader SINGLE_INSTANCE;

    private InputReader() {

    }

    public static InputReader getInstance() {
        if (SINGLE_INSTANCE == null) {
            SINGLE_INSTANCE = new InputReader();
        }

        return SINGLE_INSTANCE;
    }

    public String getInput() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Input:");

        try {
            return reader.readLine();
        } catch (IOException exception) {
            throw new IllegalArgumentException(exception.getMessage());
        }
    }
}
