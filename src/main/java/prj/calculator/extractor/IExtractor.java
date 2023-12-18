package prj.calculator.extractor;

import prj.calculator.model.CalculatorInput;

import java.util.List;

public interface IExtractor {
    CalculatorInput extract(List<String> input);
}
