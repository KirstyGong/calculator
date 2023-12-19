package prj.calculator.operation;


import prj.calculator.model.Operator;

import java.util.List;

import static prj.calculator.model.Operator.MULTIPLICATION_OPERATOR;

public class MultiplicationOperation implements IOperationHandler{
    private static MultiplicationOperation SINGLE_INSTANCE;

    private final IOperationHandler nextHandler;

    private MultiplicationOperation(IOperationHandler nextHandler) {

        this.nextHandler = nextHandler;
    }

    public static MultiplicationOperation getInstance(IOperationHandler nextHandler) {
        if (SINGLE_INSTANCE == null) {
            SINGLE_INSTANCE = new MultiplicationOperation(nextHandler);
        }

        return SINGLE_INSTANCE;
    }

    public static void destroy() {
        SINGLE_INSTANCE = null;
    }

    public double handle(Operator operator, List<Double> inputs) {

        if (operator.equals(MULTIPLICATION_OPERATOR)) {
            return apply(inputs);
        }  else {
            if (nextHandler == null) {
                throw new IllegalArgumentException("Invalid Operation");
            }
            return nextHandler.handle(operator, inputs);
        }

    }

    public double apply(List<Double> inputs) {
        return inputs.stream()
                .mapToDouble(Double::doubleValue)
                .reduce((a, b) -> a * b)
                .orElse(1.0);
    }
}
