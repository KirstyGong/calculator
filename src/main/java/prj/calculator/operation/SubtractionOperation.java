package prj.calculator.operation;


import java.util.List;

public class SubtractionOperation implements IArithmeticOperation {
    private static SubtractionOperation SINGLE_INSTANCE;

    private SubtractionOperation() {

    }

    public static SubtractionOperation getInstance() {
        if (SINGLE_INSTANCE == null) {
            SINGLE_INSTANCE = new SubtractionOperation();
        }

        return SINGLE_INSTANCE;
    }

    @Override
    public double apply(List<Double> inputs) {
        return inputs.stream()
                .mapToDouble(Double::doubleValue)
                .reduce((a, b) -> a - b)
                .orElse(0.0);
    }
}
