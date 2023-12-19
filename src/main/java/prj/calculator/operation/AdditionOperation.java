package prj.calculator.operation;


import prj.calculator.model.Operator;

import java.util.List;

import static prj.calculator.model.Operator.ADDITION_OPERATOR;

public class AdditionOperation implements IOperationHandler {

    private static AdditionOperation SINGLE_INSTANCE;

    private final IOperationHandler nextHandler;

    private AdditionOperation(IOperationHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public static AdditionOperation getInstance(IOperationHandler nextHandler) {
        if (SINGLE_INSTANCE == null) {
            SINGLE_INSTANCE = new AdditionOperation(nextHandler);
        }

        return SINGLE_INSTANCE;
    }

    /**
     * Use for test purpose only
     */
    public static void destroy() {
        SINGLE_INSTANCE = null;
    }

    public double handle(Operator operator, List<Double> inputs) {

        if (operator.equals(ADDITION_OPERATOR)) {
            return apply(inputs);
        } else {
            if (nextHandler == null) {
                throw new IllegalArgumentException("Invalid Operator");
            }
            return nextHandler.handle(operator, inputs);
        }

    }

    private double apply(List<Double> inputs) {
        return inputs.stream()
                .mapToDouble(Double::doubleValue)
                .sum();
    }

}
