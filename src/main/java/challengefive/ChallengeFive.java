package challengefive;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//TO:DO this can be written reading a list of strings and then write a method that can be used to map a value as part of a stream and collect to an immutable list of vents.
public class ChallengeFive {

    public static List<Vent> readVentDataFromFile(String fileValues, String seperatorPattern) {
        BufferedReader bufReader;
        List<String > ventData = new ArrayList<>();
        {
            try {
                bufReader = new BufferedReader(new FileReader(fileValues));
                String line = bufReader.readLine();
                while (line != null) {
                    ventData.add(line);
                    line = bufReader.readLine();
                }
                bufReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return convertToVents(seperatorPattern, ventData);
    }

    private static List<Vent> convertToVents(String seperatorPattern, List<String> ventData) {
        List<Vent> listOfVents = new ArrayList<>();
        for(String data : ventData){
            String[] firstSplit = data.split(seperatorPattern);
            String[] x1y1 = firstSplit[0].trim().split(",");
            String[] x2y2 = firstSplit[1].trim().split(",");
            Vent vent = new Vent(x1y1, x2y2);
            listOfVents.add(vent);
        }
        return listOfVents;
    }
}
