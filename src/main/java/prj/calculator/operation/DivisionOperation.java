package prj.calculator.operation;


import java.util.List;

public class DivisionOperation implements IArithmeticOperation {

    private static DivisionOperation SINGLE_INSTANCE;

    private DivisionOperation() {

    }

    public static DivisionOperation getInstance() {
        if (SINGLE_INSTANCE == null) {
            SINGLE_INSTANCE = new DivisionOperation();
        }

        return SINGLE_INSTANCE;
    }


    @Override
    public double apply(List<Double> inputs) {
        return inputs.stream()
                .mapToDouble(Double::doubleValue)
                .reduce((a, b) -> {
                    if (b == 0.0) {
                        throw new IllegalArgumentException("Division by zero");
                    }
                    return a / b;
                })
                .orElseThrow();
    }
}
