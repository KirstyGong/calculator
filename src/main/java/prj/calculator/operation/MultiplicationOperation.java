package prj.calculator.operation;


import java.util.List;

public class MultiplicationOperation implements IArithmeticOperation {
    private static MultiplicationOperation SINGLE_INSTANCE;

    private MultiplicationOperation() {

    }

    public static MultiplicationOperation getInstance() {
        if (SINGLE_INSTANCE == null) {
            SINGLE_INSTANCE = new MultiplicationOperation();
        }

        return SINGLE_INSTANCE;
    }

    @Override
    public double apply(List<Double> inputs) {
        return inputs.stream()
                .mapToDouble(Double::doubleValue)
                .reduce((a, b) -> a * b)
                .orElse(1.0);
    }
}
