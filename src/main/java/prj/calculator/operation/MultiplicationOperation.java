package prj.calculator.operation;


public class MultiplicationOperation implements IArithmeticOperation {
    private static MultiplicationOperation SINGLE_INSTANCE;

    private MultiplicationOperation() {

    }

    public static MultiplicationOperation getInstance() {
        if (SINGLE_INSTANCE == null) {
            SINGLE_INSTANCE = new MultiplicationOperation();
        }

        return SINGLE_INSTANCE;
    }

    public double apply(double a, double b) {
        return a * b;
    }
}
