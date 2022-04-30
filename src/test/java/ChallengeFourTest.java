import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class ChallengeFourTest {

    private final static String TEST_DATA_FILE = "/Users/JDY22/AdventCode/src/advent/com/bingo_test.txt";
    private final static int LAST_BINGO_NUMBER = 65;
    private final static int NUMBER_OF_BINGO_CARDS = 2;
    private final static int NO_WINNERS = -1;
    private final static int WINNING_BINGO_CARD = 0;
    private final static int WINNING_BINGO_SCORE = 572 * 13;


    @Test
    public void shouldReadBingoNumbersFromFile(){

        ChallengeFour challengeFour = new ChallengeFour();

        List<Integer> bingoNumbers = challengeFour.readBingoNumbersFromFile(TEST_DATA_FILE);
        int actualLastBingoNumber = bingoNumbers.get(bingoNumbers.size() - 1);

        assertEquals(LAST_BINGO_NUMBER, actualLastBingoNumber);

    }

    @Test
    public void shouldReadBingoCardsFromFile(){

        ChallengeFour challengeFour = new ChallengeFour();

        List<LinkedHashMap<Integer, Boolean>> bingoCards = challengeFour.readBingoCardsFromFile(TEST_DATA_FILE, "");
        int actualNumberOfBingoCards = bingoCards.size();

        assertEquals(NUMBER_OF_BINGO_CARDS, actualNumberOfBingoCards);

    }

    @Test
    public void bingoCardOneShouldWin(){

        ChallengeFour challengeFour = new ChallengeFour();

        List<Integer> bingoNumbers = challengeFour.readBingoNumbersFromFile(TEST_DATA_FILE);
        List<LinkedHashMap<Integer, Boolean>> bingoCards = challengeFour.readBingoCardsFromFile(TEST_DATA_FILE, "");

        BingoGame bingoGame = new BingoGame(bingoCards, bingoNumbers);

        int winningBingoCard = bingoGame.playBingo();

        assertEquals(WINNING_BINGO_CARD, winningBingoCard);

    }

    @Test void shouldCalculateWinningScore(){

        ChallengeFour challengeFour = new ChallengeFour();

        List<Integer> bingoNumbers = challengeFour.readBingoNumbersFromFile(TEST_DATA_FILE);
        List<LinkedHashMap<Integer, Boolean>> bingoCards = challengeFour.readBingoCardsFromFile(TEST_DATA_FILE, "");

        BingoGame bingoGame = new BingoGame(bingoCards, bingoNumbers);

        int winningBingoCard = bingoGame.playBingo();

        if (winningBingoCard != NO_WINNERS)
            assertEquals(WINNING_BINGO_SCORE, bingoGame.calculateWinningScore(bingoCards.get(winningBingoCard)));
        else
            fail();
    }



}