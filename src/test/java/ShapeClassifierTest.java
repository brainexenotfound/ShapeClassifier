import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.*;

public class ShapeClassifierTest {

    /**
     * This is an example test 
     */
    @DisplayName("Example Test")
    @Test
    public void example() {
        ShapeClassifier s = new ShapeClassifier();
        String answer = s.evaluateGuess("Equilateral,Large,Yes,100,100,100");
        assertNotEquals("Yes", answer);
    }

    @DisplayName("Test with Equilateral Triangle")
    @Test
    public void testEquilateralTriangle() {
        ShapeClassifier s = new ShapeClassifier();
        String answer = s.evaluateGuess("Equilateral,Small,Yes,50,50,50");
        assertEquals("Equilateral Triangle", answer);
    }

    @DisplayName("Test with Scalene Triangle")
    @Test
    public void testScaleneTriangle() {
        ShapeClassifier s = new ShapeClassifier();
        String answer = s.evaluateGuess("Scalene,Medium,No,70,80,90");
        assertEquals("Scalene Triangle", answer);
    }

    @DisplayName("Test with Invalid Shape Input")
    @Test
    public void testInvalidShape() {
        ShapeClassifier s = new ShapeClassifier();
        String answer = s.evaluateGuess("Circular,Large,Yes,100,100,100");
        assertEquals("Invalid Shape", answer);
    }

    @DisplayName("Test with Missing Attributes")
    @Test
    public void testMissingAttributes() {
        ShapeClassifier s = new ShapeClassifier();
        String answer = s.evaluateGuess("Isosceles,");
        assertEquals("Invalid Input", answer);
    }

    @DisplayName("Test with Boundary Side Lengths")
    @Test
    public void testBoundarySideLengths() {
        ShapeClassifier s = new ShapeClassifier();
        String answer = s.evaluateGuess("Right,Large,Yes,0,0,0");
        assertEquals("Invalid Side Lengths", answer);
    }
}
