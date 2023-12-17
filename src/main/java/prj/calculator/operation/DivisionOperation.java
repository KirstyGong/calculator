package prj.calculator.operation;


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

    public double apply(double a, double b) {

        if (a != 0 && b == 0) {
            throw new IllegalArgumentException("Can not divide 0");
        }

        return a / b;
    }

}
