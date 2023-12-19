package prj.calculator.reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class TwoInputArithmeticReader implements IInputReader {

    private static TwoInputArithmeticReader SINGLE_INSTANCE;

    private TwoInputArithmeticReader() {

    }

    public static TwoInputArithmeticReader getInstance() {
        if (SINGLE_INSTANCE == null) {
            SINGLE_INSTANCE = new TwoInputArithmeticReader();
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
