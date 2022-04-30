import java.util.*;
import java.util.stream.Collectors;

public class BingoGame {

    private static final int NUMBER_OF_ROWS_AND_COLUMNS = 5;
    private static final int NO_WINNER = -1;

    private List<LinkedHashMap<Integer, Boolean>> bingoCards;
    private List<Integer> bingoNumbers;
    private int lastNumberCalled = 0;

    public BingoGame(List<LinkedHashMap<Integer, Boolean>> bingoCards, List<Integer> bingoNumbers) {
        this.bingoCards = bingoCards;
        this.bingoNumbers = bingoNumbers;
    }

    public int playBingo(){
        int winningBingoCard = NO_WINNER;

        for(int bingoNumber : bingoNumbers){
            winningBingoCard = setFlagForBingoNumberAndCheckForWin(bingoCards, bingoNumber);
            if (winningBingoCard != NO_WINNER){
                lastNumberCalled = bingoNumber;
                return winningBingoCard;
            }
        }
        return  winningBingoCard;
    }

    public int calculateWinningScore(LinkedHashMap<Integer, Boolean> winningBingoCard){

        int totalOfUncalledNumbers = winningBingoCard.entrySet().stream().filter(number -> Boolean.FALSE.equals(number.getValue())).map(Map.Entry::getKey).reduce(0, Integer::sum);
        return totalOfUncalledNumbers * lastNumberCalled;
    }

    private int setFlagForBingoNumberAndCheckForWin(List<LinkedHashMap<Integer, Boolean>> bingoCards, int number) {

        for (Map<Integer, Boolean> bingoCard : bingoCards) {
            List<Integer> bingoNumbersSoFarInRows = new ArrayList<>();
            List<Integer> bingoNumbersSoFarInColumns = new ArrayList<>();

            Optional<Map.Entry<Integer, Boolean>> entryInMap =
                    bingoCard.entrySet().stream().filter(bingoEntry -> bingoEntry.getKey().equals(number)).findAny();
            List<Integer> bingoNumbersForCard = bingoCard.keySet().stream().collect(Collectors.toUnmodifiableList());
            if (entryInMap.isPresent()) {
                entryInMap.get().setValue(true);
                Integer bingoNumberRow = bingoNumbersForCard.indexOf(number) / NUMBER_OF_ROWS_AND_COLUMNS;
                Integer bingoNumberColumn = bingoNumbersForCard.indexOf(number) % NUMBER_OF_ROWS_AND_COLUMNS;

                bingoNumbersSoFarInRows.addAll(bingoCard.keySet().stream().filter(bingoNumber -> bingoNumberRow.equals(bingoNumbersForCard.indexOf(bingoNumber) / NUMBER_OF_ROWS_AND_COLUMNS))
                                                        .filter(bingoNumber -> bingoCard.get(bingoNumber).equals(Boolean.TRUE)).collect(Collectors.toList()));
                bingoNumbersSoFarInColumns.addAll(bingoCard.keySet().stream().filter(bingoNumber -> bingoNumberColumn.equals(bingoNumbersForCard.indexOf(bingoNumber) % NUMBER_OF_ROWS_AND_COLUMNS))
                                                        .filter(bingoNumber -> bingoCard.get(bingoNumber).equals(Boolean.TRUE)).collect(Collectors.toList()));
                if ((bingoNumbersSoFarInRows.size() == NUMBER_OF_ROWS_AND_COLUMNS) || (bingoNumbersSoFarInColumns.size() == NUMBER_OF_ROWS_AND_COLUMNS)) {
                    return bingoCards.indexOf(bingoCard);
                }
            }
        }
        return NO_WINNER;
    }
}

