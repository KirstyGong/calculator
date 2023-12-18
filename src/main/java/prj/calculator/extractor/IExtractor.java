package prj.calculator.extractor;

import prj.calculator.model.CalculatorInput;

public interface IExtractor {
    CalculatorInput extract(String input);
}
