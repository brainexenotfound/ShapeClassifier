import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ShapeClassifierTest {

    @Test
    public void testValidRectangleInput() {
        String result = ShapeClassifier.evaluateGuesses("Rectangle,Large,Yes,50,50,50,50");
        assertEquals("Yes", result);
    }

    @Test
    public void testIncorrectShapeGuess() {
        String result = ShapeClassifier.evaluateGuesses("Circle,Large,Yes,50,50,50,50");
        assertTrue(result.contains("Shape Guess Incorrect: Did you mean Rectangle?"));
    }

    @Test
    public void testIncorrectSizeGuess() {
        String result = ShapeClassifier.evaluateGuesses("Rectangle,Small,Yes,50,50,50,50");
        assertTrue(result.contains("Size Guess Incorrect"));
    }

    @Test
    public void testIncorrectEvenOddGuess() {
        String result = ShapeClassifier.evaluateGuesses("Rectangle,Large,No,50,50,50,50");
        assertTrue(result.contains("Even/Odd Guess Incorrect"));
    }

    @Test
    public void testMultipleIncorrectGuesses() {
        String result = ShapeClassifier.evaluateGuesses("Circle,Small,No,50,50,50,50");
        assertTrue(result.contains("Shape Guess Incorrect"));
        assertTrue(result.contains("Size Guess Incorrect"));
        assertTrue(result.contains("Even/Odd Guess Incorrect"));
    }

    @Test
    public void testMoreThanThreeIncorrectGuesses() {
        String result = ShapeClassifier.evaluateGuesses("Line,Small,No,1");
        assertEquals("Error: More than 3 incorrect guesses", result);
    }

    @Test
    public void testValidEquilateralTriangle() {
        String result = ShapeClassifier.evaluateGuesses("Equilateral,Large,Yes,100,100,100");
        assertEquals("Yes", result);
    }

    @Test
    public void testValidIsoscelesTriangle() {
        String result = ShapeClassifier.evaluateGuesses("Isosceles,Small,No,5,5,3");
        assertEquals("Yes", result);
    }

    @Test
    public void testInvalidRectangleParameters() {
        String result = ShapeClassifier.evaluateGuesses("Rectangle,Large,Yes,50");
        assertEquals("Error: Insufficient parameters", result);
    }

    @Test
    public void testValidCircleInput() {
        String result = ShapeClassifier
