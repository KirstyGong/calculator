package prj.calculator.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class OperatorTest {

    @Test
    void testEnumNotNull() {
        assertNotNull(Operator.ADDITION_OPERATOR);
        assertNotNull(Operator.SUBTRACTION_OPERATOR);
        assertNotNull(Operator.MULTIPLICATION_OPERATOR);
        assertNotNull(Operator.DIVISION_OPERATOR);
    }

    @Test
    void testEnumCustomMethod() {
        assertEquals("+", Operator.ADDITION_OPERATOR.value());
        assertEquals("-", Operator.SUBTRACTION_OPERATOR.value());
        assertEquals("*", Operator.MULTIPLICATION_OPERATOR.value());
        assertEquals("/", Operator.DIVISION_OPERATOR.value());
    }

    @Test
    void testEnumStringRepresentation() {
        assertEquals("+", Operator.ADDITION_OPERATOR.toString());
        assertEquals("-", Operator.SUBTRACTION_OPERATOR.toString());
        assertEquals("*", Operator.MULTIPLICATION_OPERATOR.toString());
        assertEquals("/", Operator.DIVISION_OPERATOR.toString());
    }

    @Test
    void testGetEnum() {
        assertEquals(Operator.ADDITION_OPERATOR, Operator.getEnum("+"));
        assertEquals(Operator.SUBTRACTION_OPERATOR, Operator.getEnum("-"));
        assertEquals(Operator.MULTIPLICATION_OPERATOR, Operator.getEnum("*"));
        assertEquals(Operator.DIVISION_OPERATOR, Operator.getEnum("/"));
    }
}
