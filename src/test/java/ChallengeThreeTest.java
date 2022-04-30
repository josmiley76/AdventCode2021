

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

class ChallengeThreeTest {

    @Test
    public void ShouldCalculateBinaryStates(){
        final List<String> TEST_VALUES = Arrays.asList("000000010000", "000000001001", "000000000100", "000000000011", "000000000001");
        final int[] EXPECTED_BINARY_STATE_COUNT_FOR_ONES = {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 3};
        final int[] EXPECTED_BINARY_STATE_COUNT_FOR_ZEROS = {5, 5, 5, 5, 5, 5, 5, 4, 4, 4, 4, 2};
        ChallengeThree.calculateBinaryStateCounts(TEST_VALUES);
        assertArrayEquals(EXPECTED_BINARY_STATE_COUNT_FOR_ONES, ChallengeThree.getBinaryStateCountForOnes());
        assertArrayEquals(EXPECTED_BINARY_STATE_COUNT_FOR_ZEROS, ChallengeThree.getBinaryStateCountForZeros());

    }

    @Test
    public void ShouldCalculateGammaRate(){
        final int GAMMA_RATE = 0x12;
        final List<String> TEST_VALUES = Arrays.asList("000000010010", "000000011001", "000000010110", "000000010111", "000000001000");

        ChallengeThree.calculateBinaryStateCounts(TEST_VALUES);
        assertEquals(GAMMA_RATE,ChallengeThree.calculateGammaRate());
    }

    @Test
    public void ShouldCalculateEpsilonRate(){
        final int EPSILON_RATE = 0xFED;
        final List<String> TEST_VALUES = Arrays.asList("000000010010", "000000011001", "000000010110", "000000010111", "000000001000");

        ChallengeThree.calculateBinaryStateCounts(TEST_VALUES);
        assertEquals(EPSILON_RATE,ChallengeThree.calculateEpsilonRate());
    }

    @Test
    public void ShouldCalculatePowerConsumption(){
        final List<String> TEST_VALUES = Arrays.asList("000000010010", "000000011001", "000000010110", "000000010111", "000000001000");

        final int POWER_CONSUMPTION = 0x12 * 0xFED;

        ChallengeThree.calculateBinaryStateCounts(TEST_VALUES);
        int powerConsumption = ChallengeThree.calculateGammaRate() * ChallengeThree.calculateEpsilonRate();
        assertEquals(POWER_CONSUMPTION, powerConsumption);
    }

    @Test
    public void ShouldCalculateOxygenGeneratorRating(){
        final List<String> TEST_VALUES = Arrays.asList("000000010010", "000000011001", "000000010110", "000000010111", "000000001000");
        final long OXYGEN_GENERATOR_VALUE = 23;

        long oxygenGeneratorValue = ChallengeThree.calculateOxygenGeneratorRating(TEST_VALUES);
        assertEquals(OXYGEN_GENERATOR_VALUE, oxygenGeneratorValue);
    }

    @Test
    public void ShouldCalculateCO2ScrubberRating(){
        final List<String> TEST_VALUES = Arrays.asList("000000010010", "000000011001", "000000010110", "000000010111", "000000001000");
        final long CO2_SCRUBBER_RATING = 8;

        long  co2ScrubberRating = ChallengeThree.calculateCO2ScrubberRating(TEST_VALUES);
        assertEquals(CO2_SCRUBBER_RATING, co2ScrubberRating);
    }

}