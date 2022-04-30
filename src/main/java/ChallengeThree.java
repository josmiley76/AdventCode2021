import java.util.List;
import java.util.stream.Collectors;

public class ChallengeThree {

    private static final int[] BITS_TO_TEST = {0x800, 0x400, 0x200, 0x100, 0x80, 0x40, 0x20, 0x10, 0x08, 0x04, 0x02, 0x01};

    private static int[] binaryStateCountForOnes;
    private static int[] binaryStateCountForZeros;

    public static int[] getBinaryStateCountForOnes() {
        return binaryStateCountForOnes;
    }

    public static int[] getBinaryStateCountForZeros() {
        return binaryStateCountForZeros;
    }

    public static void calculateBinaryStateCounts(List<String> valuesForCalculation) {
        binaryStateCountForOnes = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        binaryStateCountForZeros = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

        for (String valueAsString : valuesForCalculation) {
            for (int bitIndex = 0; bitIndex < valueAsString.length(); bitIndex++) {
                if ((Character.getNumericValue(valueAsString.charAt(bitIndex)) == 1)) {
                    binaryStateCountForOnes[bitIndex]++;
                } else {
                    binaryStateCountForZeros[bitIndex]++;
                }
            }
        }
    }

    public static int calculateGammaRate() {
        int gammaRate = 0;

        for (int bit = 0; bit < BITS_TO_TEST.length; bit++) {
            if (binaryStateCountForOnes[bit] > binaryStateCountForZeros[bit]) {
                gammaRate = gammaRate | (0x800 >> bit);
            }
        }
        return gammaRate;
    }

    public static int calculateEpsilonRate() {
        int epsilonRate = 0;

        for (int bit = 0; bit < BITS_TO_TEST.length; bit++) {
            if (binaryStateCountForOnes[bit] < binaryStateCountForZeros[bit]) {
                epsilonRate = epsilonRate | (0x800 >> bit);
            }
        }
        return epsilonRate;
    }

    public static long calculateOxygenGeneratorRating(List<String> valuesForCalculation) {
        List<String> validValues = valuesForCalculation;
        int bitIndex = 0;

        do{
            ChallengeThree.calculateBinaryStateCounts(validValues);
            int bitValue = Integer.parseInt((binaryStateCountForOnes[bitIndex] >= binaryStateCountForZeros[bitIndex]) ? "1" : "0");
            int finalBitIndex = bitIndex;

            validValues = validValues.stream().filter(value -> Character.getNumericValue(value.charAt(finalBitIndex)) == bitValue)
                    .collect(Collectors.toList());
            bitIndex++;
        }
        while ((validValues.size() != 1) && (bitIndex < binaryStateCountForOnes.length));

        return Long.parseLong(validValues.get(0),2);
    }

    public static long calculateCO2ScrubberRating(List<String> valuesForCalculation) {
        List<String> validValues = valuesForCalculation;
        int bitIndex = 0;

        do{
            ChallengeThree.calculateBinaryStateCounts(validValues);
            int bitValue = Integer.parseInt(((binaryStateCountForOnes[bitIndex] > 0) && (binaryStateCountForOnes[bitIndex] < binaryStateCountForZeros[bitIndex])) ? "1" : "0");
            int finalBitIndex = bitIndex;

            validValues = validValues.stream().filter(value -> Character.getNumericValue(value.charAt(finalBitIndex)) == bitValue)
                    .collect(Collectors.toList());
            bitIndex++;
        }
        while ((validValues.size() != 1) && (bitIndex < binaryStateCountForOnes.length));

        return Long.parseLong(validValues.get(0), 2);
    }
}
