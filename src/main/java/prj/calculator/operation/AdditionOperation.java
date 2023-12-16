package prj.calculator.operation;


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

    public double apply(double a, double b) {
        return a + b;
    }

}
