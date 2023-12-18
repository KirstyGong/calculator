package prj.calculator.reader;

import prj.calculator.operation.AdditionOperation;
import prj.calculator.util.IValidator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

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

    public List<String> getInput() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            List<String> inputs = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                System.out.println("Input:");

                inputs.add(reader.readLine());
            }

            return inputs;

        } catch (IOException exception) {
            throw new IllegalArgumentException(exception.getMessage());
        }
    }
}
