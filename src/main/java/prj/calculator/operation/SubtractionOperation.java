package prj.calculator.operation;


import prj.calculator.model.Operator;

import java.util.List;

import static prj.calculator.model.Operator.DIVISION_OPERATOR;
import static prj.calculator.model.Operator.SUBTRACTION_OPERATOR;

public class SubtractionOperation implements IOperationHandler{
    private static SubtractionOperation SINGLE_INSTANCE;

    private final IOperationHandler nextHandler;

    private SubtractionOperation(IOperationHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public static SubtractionOperation getInstance(IOperationHandler nextHandler) {
        if (SINGLE_INSTANCE == null) {
            SINGLE_INSTANCE = new SubtractionOperation(nextHandler);
        }

        return SINGLE_INSTANCE;
    }

    public static void destroy() {
        SINGLE_INSTANCE = null;
    }

    public double handle(Operator operator, List<Double> inputs) {

        if (operator.equals(SUBTRACTION_OPERATOR)) {
            return apply(inputs);
        }  else {
            if (nextHandler == null) {
                throw new IllegalArgumentException("Invalid operator");
            }
            return nextHandler.handle(operator, inputs);
        }

    }

    private double apply(List<Double> inputs) {
        return inputs.stream()
                .mapToDouble(Double::doubleValue)
                .reduce((a, b) -> a - b)
                .orElse(0.0);
    }
}
