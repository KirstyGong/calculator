package prj.calculator.operation;


public class SubtractionOperation implements IArithmeticOperation {
    private static SubtractionOperation SINGLE_INSTANCE;

    private SubtractionOperation() {

    }

    public static SubtractionOperation getInstance() {
        if (SINGLE_INSTANCE == null) {
            SINGLE_INSTANCE = new SubtractionOperation();
        }

        return SINGLE_INSTANCE;
    }

    public double apply(double a, double b) {
        return a - b;
    }

}
