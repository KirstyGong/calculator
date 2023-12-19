package prj.calculator.operation;


import prj.calculator.model.Operator;

import java.util.List;

import static prj.calculator.model.Operator.DIVISION_OPERATOR;

public class DivisionOperation implements IOperationHandler{

    private static DivisionOperation SINGLE_INSTANCE;

    private final IOperationHandler nextHandler;

    private DivisionOperation(IOperationHandler nextHandler) {

        this.nextHandler = nextHandler;
    }

    public static DivisionOperation getInstance(IOperationHandler nextHandler) {
        if (SINGLE_INSTANCE == null) {
            SINGLE_INSTANCE = new DivisionOperation(nextHandler);
        }

        return SINGLE_INSTANCE;
    }

    public static void destroy() {
        SINGLE_INSTANCE = null;
    }

    public double handle(Operator operator, List<Double> inputs) {

        if (operator.equals(DIVISION_OPERATOR)) {
            return apply(inputs);
        }  else {
            if (nextHandler == null) {
                throw new IllegalArgumentException("Invalid operation");
            }
            return nextHandler.handle(DIVISION_OPERATOR, inputs);
        }

    }

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
