import challengefive.ChallengeFive;
import challengefive.Vent;
import challengefive.VentCalculations;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        ChallengeOne challengeOne = new ChallengeOne();
        final int NO_WINNER = -1;

        List<String> testValueList = challengeOne.readValuesFromFile("/Users/JDY22/AdventCode/src/advent/com/file.txt");
        System.out.println(testValueList.size());
        System.out.println(challengeOne.countValuesGreaterThanPreviousValue(testValueList));

        List<String> submarineDirectionsList = challengeOne.readValuesFromFile("/Users/JDY22/AdventCode/src/advent/com/subdirections.txt");
        for(String position : submarineDirectionsList){
            ChallengeTwo.calculatePosition(position);
        }
        System.out.println("Submarine Position = " + ChallengeTwo.getFinalSubmarinePosition());

        List<String> submarinePositionsList = challengeOne.readValuesFromFile("/Users/JDY22/AdventCode/src/advent/com/positions.txt");
        ChallengeThree.calculateBinaryStateCounts(submarinePositionsList);
        System.out.println("Power Consumption = " + ChallengeThree.calculateGammaRate() * ChallengeThree.calculateEpsilonRate());
        ChallengeThree.calculateBinaryStateCounts(submarinePositionsList);
        System.out.println("Life Support Rating = " + ChallengeThree.calculateCO2ScrubberRating(submarinePositionsList) * ChallengeThree.calculateOxygenGeneratorRating(submarinePositionsList));

        ChallengeFour challengeFour = new ChallengeFour();


        List<Integer> bingoNumbers = challengeFour.readBingoNumbersFromFile("/Users/JDY22/AdventCode/src/advent/com/bingo.txt");
        List<LinkedHashMap<Integer, Boolean>> bingoCards = challengeFour.readBingoCardsFromFile("/Users/JDY22/AdventCode/src/advent/com/bingo.txt", "");

        BingoGame bingoGame = new BingoGame(bingoCards, bingoNumbers);

        int winningBingoCard = bingoGame.playBingo();

        if (winningBingoCard != NO_WINNER){
            System.out.println("Winning Bingo Card Total = " + bingoGame.calculateWinningScore(bingoCards.get(winningBingoCard)));
        }

        List<Vent> ventsList = ChallengeFive.readVentDataFromFile("/Users/JDY22/AdventCode/src/advent/com/challengefive/ventData.txt", "->");

        System.out.println("Number of Dangerous Vents is " + VentCalculations.calculateTotalDangerousPoints(ventsList));
    }
}
