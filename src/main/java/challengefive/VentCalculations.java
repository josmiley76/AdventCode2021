package challengefive;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.tuple.Pair;

public class VentCalculations {
    private final static int INVALID_COORDINATE_VALUE = -1;
    private final static int DANGEROUS_VENTS = 2;
//    private List<Quartet> vents = new List<Quartet> ();

    public static int calculateMinCoordinate(List<Vent> vents, Coordinate coordinate){
        switch (coordinate){
            case X1: return vents.stream().mapToInt(Vent::getX1).min().orElse(INVALID_COORDINATE_VALUE);
            case X2: return vents.stream().mapToInt(Vent::getX2).min().orElse(INVALID_COORDINATE_VALUE);
            case Y1: return vents.stream().mapToInt(Vent::getY1).min().orElse(INVALID_COORDINATE_VALUE);
            case Y2: return vents.stream().mapToInt(Vent::getY1).min().orElse(INVALID_COORDINATE_VALUE);
            default: return INVALID_COORDINATE_VALUE;
        }
    }

    public static int calculateMaxCoordinate(List<Vent> vents, Coordinate coordinate) {
        switch (coordinate){
            case X1: return vents.stream().mapToInt(Vent::getX1).max().orElse(INVALID_COORDINATE_VALUE);
            case X2: return vents.stream().mapToInt(Vent::getX2).max().orElse(INVALID_COORDINATE_VALUE);
            case Y1: return vents.stream().mapToInt(Vent::getY1).max().orElse(INVALID_COORDINATE_VALUE);
            case Y2: return vents.stream().mapToInt(Vent::getY1).max().orElse(INVALID_COORDINATE_VALUE);
            default: return INVALID_COORDINATE_VALUE;
        }
    }

    public static long calculateTotalDangerousPoints(List<Vent> vents, boolean includeDiagonals) {
        int yDimension = Math.max(VentCalculations.calculateMaxCoordinate(vents, Coordinate.Y1), VentCalculations.calculateMaxCoordinate(vents, Coordinate.Y2)) + 1;
        int xDimension = Math.max(VentCalculations.calculateMaxCoordinate(vents, Coordinate.X1), VentCalculations.calculateMaxCoordinate(vents, Coordinate.X2)) + 1;
        int[][] ventGrid = new int [yDimension][xDimension];
        List <Vent> ventsWithoutDiagonals = vents.stream()
                                                 .filter(vent -> (vent.getX1() == vent.getX2()) || (vent.getY1() == vent.getY2()))
                                                 .collect(Collectors.toUnmodifiableList());
        List <Vent> ventsWithDiagonals = vents.stream()
                                              .filter(vent -> ((vent.getX1() == vent.getY1()) && (vent.getX2() == vent.getY2())) || (vent.getX1() == vent.getY2()) && vent.getX2() == vent.getY1())
                                              .collect(Collectors.toUnmodifiableList());
        List<Vent> ventsToCheck = new ArrayList<>(ventsWithoutDiagonals);
        if (includeDiagonals)
            ventsToCheck.addAll(ventsWithDiagonals);

        for (Vent vent : ventsToCheck) {
            if ((vent.getX1() == vent.getY1()) && (vent.getX2() == vent.getY2())) {
                int start = Math.min(vent.getX1(), vent.getX2());
                int end = Math.max(vent.getX1(), vent.getX2());
                for (int ypos = start; ypos <= end; ypos++) {
                    ventGrid[ypos][ypos]++;
                }
            } else if ((vent.getX1() == vent.getY2()) && vent.getX2() == vent.getY1()){
                int start = Math.min(vent.getY1(), vent.getY2());
                int end = Math.max(vent.getY1(), vent.getY2());
                for (int ypos = start; ypos <= end; ypos++) {
                    ventGrid[ypos][end - ypos]++;
                }
            } else if (vent.getX1() != vent.getX2()) {
                int start = Math.min(vent.getX1(), vent.getX2());
                int end = Math.max(vent.getX1(), vent.getX2());
                for (int xpos = start; xpos <= end; xpos++) {
                    ventGrid[vent.getY1()][xpos]++;
                }
            } else if (vent.getY1() != vent.getY2()) {
                int start = Math.min(vent.getY1(), vent.getY2());
                int end = Math.max(vent.getY1(), vent.getY2());
                for (int ypos = start; ypos <= end; ypos++) {
                    ventGrid[ypos][vent.getX1()]++;
                }
            }
        }

        return Arrays.stream(ventGrid).flatMapToInt(vent -> Arrays.stream(vent).filter(v -> v >= DANGEROUS_VENTS))
                     .count();
  //you want to go through each horizontal line in turn adding one to each Y location that matchesY1 = Y2, and then repeat
//        for the vertical lines - where X1 = X2 - don't bother where both are different.
    }
    //use a tuple ?//
}
