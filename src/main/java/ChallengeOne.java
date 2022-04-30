import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ChallengeOne {

    public List<String> readValuesFromFile(String fileValues) {
        BufferedReader bufReader;
        ArrayList<String> listOfLines = new ArrayList<>();

        {
            try {
                bufReader = new BufferedReader(new FileReader(fileValues));
                String line = bufReader.readLine();
                while (line != null) {
                    listOfLines.add(line);
                    line = bufReader.readLine();
                }
                bufReader.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return listOfLines;
    }

    public int countValuesGreaterThanPreviousValue(List<String> testValues){
        int count = 0;
        int previousValue = Integer.parseInt(testValues.get(0));

        for(String value : testValues) {
            int numericalValue = Integer.parseInt(value);
            if(numericalValue > previousValue){
                count++;
            }
            previousValue = numericalValue;
        }
        return count;
    }
}
