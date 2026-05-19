package com.example;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class CalculatorTest {

    @Test
    void testAdd() {
        assertEquals(5, new Calculator().add(2, 3));
    }

    @Test
    void testSubtract() {
        assertEquals(1, new Calculator().subtract(3, 2));
    }

    @Test
    void testMultiply() {
        assertEquals(6, new Calculator().multiply(2, 3));
    }

    @Test
    void testDivide() {
        assertEquals(2, new Calculator().divide(6, 3));
    }

    @Test
    void testDivideByZero() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new Calculator().divide(6, 0)
        );
    }
}