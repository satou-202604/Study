import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {

    @Test
    public void testAdd() {

        Calculator calculator = new Calculator();

        int result = calculator.add(5, 3);

        assertEquals(8, result);
    }

    @Test
    public void testSubtract() {

        Calculator calculator = new Calculator();

        int result = calculator.subtract(10, 4);

        assertEquals(6, result);
    }

    @Test
    public void testDivide() {

        Calculator calculator = new Calculator();

        int result = calculator.divide(10, 2);

        assertEquals(5, result);
    }

    @Test
    public void testDivideByZero() {

        Calculator calculator = new Calculator();

        assertThrows(ArithmeticException.class, () -> {
            calculator.divide(10, 0);
        });
    }
}