import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

public class ShapeClassifierTest {

    @DisplayName("All correct guesses for Equilateral triangle")
    @Test
    public void testEquilateralTriangleCorrect() {
        ShapeClassifier s = new ShapeClassifier();
        String answer = s.evaluateGuess("Equilateral,Large,Yes,40,40,40");
        assertEquals("Yes", answer);
    }

    @DisplayName("Incorrect shape guess, correct size and even/odd guesses")
    @Test
    public void testIncorrectShapeGuessEquilateral() {
        ShapeClassifier s = new ShapeClassifier();
        String answer = s.evaluateGuess("Scalene,Large,Yes,40,40,40");
        assertEquals("No: Suggestion=Equilateral", answer.trim());
    }

    @DisplayName("Incorrect size guess, correct shape and even/odd guesses")
    @Test
    public void testIncorrectSizeGuessEquilateral() {
        ShapeClassifier s = new ShapeClassifier();
        String answer = s.evaluateGuess("Equilateral,Small,Yes,40,40,40");
        assertEquals("Wrong Size", answer);
    }

    @DisplayName("Incorrect even/odd guess, correct shape and size guesses")
    @Test
    public void testIncorrectEvenOddGuessEquilateral() {
        ShapeClassifier s = new ShapeClassifier();
        String answer = s.evaluateGuess("Equilateral,Large,No,40,40,40");
        assertEquals("Wrong Even/Odd", answer);
    }

    @DisplayName("All correct guesses for Rectangle")
    @Test
    public void testRectangleCorrect() {
        ShapeClassifier s = new ShapeClassifier();
        String answer = s.evaluateGuess("Rectangle,Large,Yes,50,30,50,30");
        assertEquals("Yes", answer);
    }

    @DisplayName("Incorrect shape guess for Rectangle")
    @Test
    public void testRectangleIncorrectShapeGuess() {
        ShapeClassifier s = new ShapeClassifier();
        String answer = s.evaluateGuess("Square,Large,Yes,50,30,50,30");
        assertEquals("No: Suggestion=Rectangle", answer.trim());
    }

    @DisplayName("All correct guesses for Circle")
    @Test
    public void testCircleCorrect() {
        ShapeClassifier s = new ShapeClassifier();
        String answer = s.evaluateGuess("Circle,Large,Yes,16,16");
        assertEquals("Yes", answer);
    }

    @DisplayName("All correct guesses for Ellipse")
    @Test
    public void testEllipseCorrect() {
        ShapeClassifier s = new ShapeClassifier();
        String answer = s.evaluateGuess("Ellipse,Large,Yes,20,30");
        assertEquals("Yes", answer);
    }

    @DisplayName("All correct guesses for Line")
    @Test
    public void testLineCorrect() {
        ShapeClassifier s = new ShapeClassifier();
        String answer = s.evaluateGuess("Line,Small,No,5");
        assertEquals("Yes", answer);
    }

    @DisplayName("Invalid shape input")
    @Test
    public void testInvalidShapeInput() {
        ShapeClassifier s = new ShapeClassifier();
        String answer = s.evaluateGuess("Hexagon,Large,Yes,10,10,10,10,10,10");
        assertEquals("No", answer);
    }

    @DisplayName("Missing attributes in input")
    @Test
    public void testMissingAttributes() {
        ShapeClassifier s = new ShapeClassifier();
        String answer = s.evaluateGuess("Isosceles,");
        assertEquals("No", answer);
    }

    @DisplayName("Negative side lengths are adjusted to 0")
    @Test
    public void testNegativeSideLengths() {
        ShapeClassifier s = new ShapeClassifier();
        String answer = s.evaluateGuess("Line,Small,No,-5");
        assertEquals("Wrong Even/Odd", answer);
    }

    @DisplayName("Side lengths exceeding 4095 are adjusted to 4095")
    @Test
    public void testExceedingSideLengths() {
        ShapeClassifier s = new ShapeClassifier();
        String answer = s.evaluateGuess("Line,Large,Yes,5000");
        assertEquals("Wrong Even/Odd", answer);
    }

    @DisplayName("Perimeter exactly 100 is not 'Large'")
    @Test
    public void testPerimeterExactly100() {
        ShapeClassifier s = new ShapeClassifier();
        String answer = s.evaluateGuess("Line,Large,Yes,50");
        assertEquals("Wrong Size", answer);
    }

    @DisplayName("Perimeter exactly 10 is not 'Small'")
    @Test
    public void testPerimeterExactly10() {
        ShapeClassifier s = new ShapeClassifier();
        String answer = s.evaluateGuess("Line,Small,Yes,10");
        assertEquals("Wrong Size", answer);
    }

    @DisplayName("Non-integer parameters")
    @Test
    public void testNonIntegerParameters() {
        ShapeClassifier s = new ShapeClassifier();
        String answer = s.evaluateGuess("Circle,Large,Yes,abc,abc");
        assertEquals("No", answer);
    }

    @DisplayName("Empty input")
    @Test
    public void testEmptyInput() {
        ShapeClassifier s = new ShapeClassifier();
        String answer = s.evaluateGuess("");
        assertEquals("No", answer);
    }

    @DisplayName("Insufficient parameters")
    @Test
    public void testInsufficientParameters() {
        ShapeClassifier s = new ShapeClassifier();
        String answer = s.evaluateGuess("Circle,Large");
        assertEquals("No", answer);
    }

    @DisplayName("Correct guesses for Line with perimeter less than 10")
    @Test
    public void testLineSmallPerimeter() {
        ShapeClassifier s = new ShapeClassifier();
        String answer = s.evaluateGuess("Line,Small,No,5");
        assertEquals("Yes", answer);
    }

    @DisplayName("Perimeter between 10 and 100 is neither 'Small' nor 'Large'")
    @Test
    public void testMediumPerimeter() {
        ShapeClassifier s = new ShapeClassifier();
        String answer = s.evaluateGuess("Line,Medium,Yes,50");
        assertEquals("No", answer);
    }

    @DisplayName("Invalid number of parameters for Rectangle")
    @Test
    public void testInvalidParametersRectangle() {
        ShapeClassifier s = new ShapeClassifier();
        String answer = s.evaluateGuess("Rectangle,Large,Yes,30,40,50");
        assertEquals("No", answer);
    }

    @DisplayName("Test with perimeter exactly at the threshold of 'Large'")
    @Test
    public void testPerimeterAtThresholdLarge() {
        ShapeClassifier s = new ShapeClassifier();
        String answer = s.evaluateGuess("Line,Large,Yes,101");
        assertEquals("Yes", answer);
    }

    @DisplayName("Test with perimeter exactly at the threshold of 'Small'")
    @Test
    public void testPerimeterAtThresholdSmall() {
        ShapeClassifier s = new ShapeClassifier();
        String answer = s.evaluateGuess("Line,Small,No,9");
        assertEquals("Yes", answer);
    }
}
