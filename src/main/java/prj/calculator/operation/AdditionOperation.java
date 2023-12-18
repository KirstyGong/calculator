package prj.calculator.operation;


import java.util.List;

public class AdditionOperation implements IArithmeticOperation {

    private static AdditionOperation SINGLE_INSTANCE;

    private AdditionOperation() {

    }

    public static AdditionOperation getInstance() {
        if (SINGLE_INSTANCE == null) {
            SINGLE_INSTANCE = new AdditionOperation();
        }

        return SINGLE_INSTANCE;
    }

    public double apply(List<Double> inputs) {
        return inputs.stream()
                .mapToDouble(Double::doubleValue)
                .sum();
    }

}
