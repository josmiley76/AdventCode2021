package challengefive;

import org.junit.jupiter.api.BeforeAll;
import org.testng.annotations.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class VentCalculationsTest {

    private final static String TEST_DATA_FILE = "/Users/JDY22/AdventCode/src/advent/com/challengefive/ventTestData.txt";
    private final static long NUMBER_OF_DANGEROUS_POINTS = 5;
    private final static int SMALLEST_X1 = 0;
    private final static int SMALLEST_Y1 = 0;
    private final static int LARGEST_X1 = 9;
    private final static int LARGEST_Y1 = 9;
    private final static int SMALLEST_X2 = 0;
    private final static int SMALLEST_Y2 = 0;
    private final static int LARGEST_X2 = 8;
    private final static int LARGEST_Y2 = 9;

    private static List<Vent> vents;

    @BeforeAll
    public static void setup(){
        vents = ChallengeFive.readVentDataFromFile(TEST_DATA_FILE, "-");
    }

    @Test
    public void shouldCalculateMinCoordinates(){

        assertEquals(SMALLEST_X1, VentCalculations.calculateMinCoordinate(vents, Coordinate.X1));
        assertEquals(SMALLEST_X2, VentCalculations.calculateMinCoordinate(vents, Coordinate.X2));
        assertEquals(SMALLEST_Y1, VentCalculations.calculateMinCoordinate(vents, Coordinate.Y1));
        assertEquals(SMALLEST_Y2, VentCalculations.calculateMinCoordinate(vents, Coordinate.Y2));
    }

    @Test
    public void shouldCalculateMaxCoordinates(){
        assertEquals(LARGEST_X1, VentCalculations.calculateMaxCoordinate(vents, Coordinate.X1));
        assertEquals(LARGEST_X2, VentCalculations.calculateMaxCoordinate(vents, Coordinate.X2));
        assertEquals(LARGEST_Y1, VentCalculations.calculateMaxCoordinate(vents, Coordinate.Y1));
        assertEquals(LARGEST_Y2, VentCalculations.calculateMaxCoordinate(vents, Coordinate.Y2));
    }
    
    @Test
    public void shouldCalculateNumberOfDangerousPointsToAvoid(){
        long actualDangerousVents = VentCalculations.calculateTotalDangerousPoints(vents);
        assertEquals(NUMBER_OF_DANGEROUS_POINTS, actualDangerousVents);
    }

}