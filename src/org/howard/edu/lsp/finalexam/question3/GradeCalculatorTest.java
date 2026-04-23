package org.howard.edu.lsp.finalexam.question3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit 5 test cases for GradeCalculator.
 */
public class GradeCalculatorTest {

    private GradeCalculator calc;

    @BeforeEach
    public void setUp() {
        calc = new GradeCalculator();
    }

    // Test 1: average()
    @Test
    public void testAverageTypicalScores() {
        assertEquals(80.0, calc.average(70, 80, 90), 0.001);
    }

    // Test 2: letterGrade()
    @Test
    public void testLetterGradeAllBands() {
        assertEquals("A", calc.letterGrade(95));
        assertEquals("B", calc.letterGrade(85));
        assertEquals("C", calc.letterGrade(75));
        assertEquals("D", calc.letterGrade(65));
        assertEquals("F", calc.letterGrade(55));
    }

    // Test 3: isPassing()
    @Test
    public void testIsPassingAboveThreshold() {
        assertTrue(calc.isPassing(75));
        assertFalse(calc.isPassing(59));
    }

    // Boundary Test 1: score exactly 0
    @Test
    public void testAverageBoundaryAllZeros() {
        assertEquals(0.0, calc.average(0, 0, 0), 0.001);
    }

    // Boundary Test 2: score exactly 100
    @Test
    public void testAverageBoundaryAllHundreds() {
        assertEquals(100.0, calc.average(100, 100, 100), 0.001);
    }

    // Exception Test 1: score below 0
    @Test
    public void testExceptionScoreBelowZero() {
        assertThrows(IllegalArgumentException.class,
            () -> calc.average(-1, 50, 50));
    }

    // Exception Test 2: score above 100
    @Test
    public void testExceptionScoreAboveHundred() {
        assertThrows(IllegalArgumentException.class,
            () -> calc.average(50, 101, 50));
    }
}