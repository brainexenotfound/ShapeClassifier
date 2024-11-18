import static org.junit.Assert.*;
import org.junit.Test;

public class ShapeClassifierTest {

    @Test
    public void testEvaluateGuess_Circle_Small_PerimeterJustBelow10() {
        // BVA: Perimeter just below 10
        ShapeClassifier sc = new ShapeClassifier();
        String result = sc.evaluateGuess("Circle,Small,Yes,1");
        assertEquals("Yes", result);
    }

    @Test
    public void testEvaluateGuess_Circle_Small_PerimeterAt10() {
        // BVA: Perimeter at 10
        ShapeClassifier sc = new ShapeClassifier();
        String result = sc.evaluateGuess("Circle,Small,Yes,2");
        // Perimeter = 2 * PI * 2 ≈ 12.57 (>10), so SizeGuess should be "Large"
        assertNotEquals("Yes", result);
        assertTrue(result.contains("Wrong Size"));
    }

    @Test
    public void testEvaluateGuess_Circle_Large_PerimeterJustAbove100() {
        // BVA: Perimeter just above 100
        ShapeClassifier sc = new ShapeClassifier();
        String result = sc.evaluateGuess("Circle,Large,Yes,16");
        assertEquals("Yes", result);
    }

    @Test
    public void testEvaluateGuess_Circle_Large_PerimeterAt100() {
        // BVA: Perimeter at 100
        ShapeClassifier sc = new ShapeClassifier();
        String result = sc.evaluateGuess("Circle,Large,Yes,15");
        // Perimeter = 2 * PI * 15 ≈ 94.25 (<100), so SizeGuess should be "Small"
        assertNotEquals("Yes", result);
        assertTrue(result.contains("Wrong Size"));
    }

    @Test
    public void testEvaluateGuess_Triangle_Scalene_CorrectGuesses() {
        // Equivalence Class: Valid scalene triangle
        ShapeClassifier sc = new ShapeClassifier();
        String result = sc.evaluateGuess("Scalene,Large,No,50,60,70");
        assertEquals("Yes", result);
    }

    @Test
    public void testEvaluateGuess_Triangle_Isosceles_IncorrectShapeGuess() {
        // Equivalence Class: Valid isosceles triangle, incorrect shape guess
        ShapeClassifier sc = new ShapeClassifier();
        String result = sc.evaluateGuess("Equilateral,Small,Yes,5,5,8");
        assertTrue(result.contains("Suggestion= Isosceles"));
    }

    @Test
    public void testEvaluateGuess_Square_PerimeterAtBoundary() {
        // BVA: Perimeter at 100
        ShapeClassifier sc = new ShapeClassifier();
        String result = sc.evaluateGuess("Square,Large,Yes,25,25,25,25");
        // Perimeter = 100
        assertNotEquals("Yes", result);
        assertTrue(result.contains("Wrong Size"));
    }

    @Test
    public void testEvaluateGuess_Square_PerimeterJustAbove100() {
        // BVA: Perimeter just above 100
        ShapeClassifier sc = new ShapeClassifier();
        String result = sc.evaluateGuess("Square,Large,Yes,26,26,26,26");
        assertEquals("Yes", result);
    }

    @Test
    public void testEvaluateGuess_Rectangle_InvalidParameters() {
        // Equivalence Class: Invalid rectangle sides
        ShapeClassifier sc = new ShapeClassifier();
        String result = sc.evaluateGuess("Rectangle,Small,Yes,10,20,10,30");
        assertTrue(result.contains("Suggestion=Square"));
    }

    @Test
    public void testEvaluateGuess_EvenOddGuessIncorrect() {
        // BVA: Even/Odd guess incorrect
        ShapeClassifier sc = new ShapeClassifier();
        String result = sc.evaluateGuess("Circle,Small,No,1");
        assertTrue(result.contains("Wrong Even/Odd"));
    }

    @Test
    public void testEvaluateGuess_NegativeParameterValue() {
        // BVA: Parameter value at lower boundary (negative, adjusted to 0)
        ShapeClassifier sc = new ShapeClassifier();
        String result = sc.evaluateGuess("Line,Small,Yes,-1");
        // Parameter adjusted to 0, perimeter = 0
        assertEquals("Yes", result);
    }

    @Test
    public void testEvaluateGuess_ParameterValueAtUpperBoundary() {
        // BVA: Parameter value at upper boundary (4095)
        ShapeClassifier sc = new ShapeClassifier();
        String result = sc.evaluateGuess("Line,Large,Yes,5000");
        // Parameter adjusted to 4095
        assertEquals("Yes", result);
    }

    @Test
    public void testEvaluateGuess_InvalidShapeGuess() {
        // Equivalence Class: Invalid shape guess
        ShapeClassifier sc = new ShapeClassifier();
        String result = sc.evaluateGuess("Pentagon,Small,Yes,10,10,10,10,10");
        assertTrue(result.contains("No"));
    }

    @Test
    public void testEvaluateGuess_InvalidSizeGuess() {
        // Equivalence Class: Invalid size guess
        ShapeClassifier sc = new ShapeClassifier();
        String result = sc.evaluateGuess("Circle,Medium,Yes,1");
        assertTrue(result.contains("Wrong Size"));
    }

    @Test
    public void testEvaluateGuess_InvalidEvenOddGuess() {
        // Equivalence Class: Invalid even/odd guess
        ShapeClassifier sc = new ShapeClassifier();
        String result = sc.evaluateGuess("Circle,Small,Maybe,1");
        assertTrue(result.contains("Wrong Even/Odd"));
    }

    @Test
    public void testEvaluateGuess_Triangle_PerimeterJustBelow10() {
        // BVA: Triangle perimeter just below 10
        ShapeClassifier sc = new ShapeClassifier();
        String result = sc.evaluateGuess("Equilateral,Small,Yes,3,3,3");
        // Perimeter = 9
        assertEquals("Yes", result);
    }

    @Test
    public void testEvaluateGuess_Triangle_PerimeterAt10() {
        // BVA: Triangle perimeter at 10
        ShapeClassifier sc = new ShapeClassifier();
        String result = sc.evaluateGuess("Equilateral,Small,Yes,3,3,4");
        // Perimeter = 10
        assertNotEquals("Yes", result);
        assertTrue(result.contains("Wrong Size"));
    }

    @Test
    public void testEvaluateGuess_Triangle_PerimeterJustAbove100() {
        // BVA: Triangle perimeter just above 100
        ShapeClassifier sc = new ShapeClassifier();
        String result = sc.evaluateGuess("Scalene,Large,Yes,40,40,30");
        // Perimeter = 110
        assertEquals("Yes", result);
    }

    @Test
    public void testEvaluateGuess_Triangle_InvalidSides() {
        // Equivalence Class: Invalid triangle sides (violates triangle inequality)
        ShapeClassifier sc = new ShapeClassifier();
        String result = sc.evaluateGuess("Scalene,Small,Yes,1,2,3");
        assertTrue(result.contains("Suggestion=Not A Triangle"));
    }

    @Test
    public void testEvaluateGuess_BadGuessLimitExceeded() {
        // BVA: Exceeding bad guess limit
        ShapeClassifier sc = new ShapeClassifier();
        sc.evaluateGuess("Circle,Large,No,1");
        sc.evaluateGuess("Circle,Large,No,1");
        String result = sc.evaluateGuess("Circle,Large,No,1");
        // The program should exit, but since System.exit(1) cannot be caught,
        // we can check if badGuesses count reached 3
        assertEquals(3, sc.badGuesses);
    }

    @Test
    public void testEvaluateGuess_Line_CorrectGuess() {
        // Equivalence Class: Line with correct guess
        ShapeClassifier sc = new ShapeClassifier();
        String result = sc.evaluateGuess("Line,Small,Yes,5");
        assertEquals("Yes", result);
    }

    @Test
    public void testEvaluateGuess_Ellipse_CorrectGuess() {
        // Equivalence Class: Ellipse with correct guess
        ShapeClassifier sc = new ShapeClassifier();
        String result = sc.evaluateGuess("Ellipse,Large,Yes,10,15");
        assertEquals("Yes", result);
    }

    @Test
    public void testEvaluateGuess_Square_IncorrectEvenOddGuess() {
        // BVA: Even/Odd guess incorrect
        ShapeClassifier sc = new ShapeClassifier();
        String result = sc.evaluateGuess("Square,Large,No,26,26,26,26");
        assertTrue(result.contains("Wrong Even/Odd"));
    }

    @Test
    public void testEvaluateGuess_Rectangle_CorrectGuess() {
        // Equivalence Class: Rectangle with correct guess
        ShapeClassifier sc = new ShapeClassifier();
        String result = sc.evaluateGuess("Rectangle,Large,Yes,20,30,20,30");
        assertEquals("Yes", result);
    }

    @Test
    public void testEvaluateGuess_InvalidParametersCount() {
        // Equivalence Class: Invalid number of parameters
        ShapeClassifier sc = new ShapeClassifier();
        String result = sc.evaluateGuess("Circle,Small,Yes");
        assertTrue(result.contains("No"));
    }

    @Test
    public void testEvaluateGuess_ZeroLengthSides() {
        // BVA: Zero-length sides
        ShapeClassifier sc = new ShapeClassifier();
        String result = sc.evaluateGuess("Line,Small,Yes,0");
        assertEquals("Yes", result);
    }

    @Test
    public void testEvaluateGuess_MaximumLengthSides() {
        // BVA: Maximum allowed parameter value (4095)
        ShapeClassifier sc = new ShapeClassifier();
        String result = sc.evaluateGuess("Line,Large,Yes,4095");
        assertEquals("Yes", result);
    }
}
