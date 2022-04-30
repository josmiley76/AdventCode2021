import challengefive.ChallengeFive;
import challengefive.Vent;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ChallengeFiveTest {

    private final static String TEST_DATA_FILE = "/Users/JDY22/AdventCode/src/advent/com/challengefive/ventTestData.txt";
    private final static int NUMBER_TEST_VENTS = 10;

    @Test
    public void shouldReadFileAndCreateVents(){

        List<Vent> vents = ChallengeFive.readVentDataFromFile(TEST_DATA_FILE,"->");

        assertEquals(NUMBER_TEST_VENTS, vents.size());
    }
}