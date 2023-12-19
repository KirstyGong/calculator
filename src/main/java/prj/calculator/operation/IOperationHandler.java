package prj.calculator.operation;


import prj.calculator.model.Operator;

import java.util.List;

public interface IOperationHandler {

    double handle(Operator operator, List<Double> inputs);

}
