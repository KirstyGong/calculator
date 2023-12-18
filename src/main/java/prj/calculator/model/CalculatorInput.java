package prj.calculator.model;

import java.util.List;

public class CalculatorInput {

    private final List<Double> operands;
    private final Operator operator;

    public CalculatorInput(List<Double> operands, Operator operators) {
        this.operands = operands;
        this.operator = operators;
    }

    public Operator getOperator() {
        return operator;
    }

    public List<Double> getOperands() {
        return operands;
    }
}
