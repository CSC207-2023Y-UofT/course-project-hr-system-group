package usecases.modifyfiles;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * ScheduleFileReader.java
 * Class for ScheduleFileReader.
 */
public class ScheduleFileReader {

    /**
     * readCSV
     * Reads the .csv at filePath and returns a List<String[]> of the data.
     * @param filePath, String for the file path of .csv to be read.
     * @return List<String[]>, compiled data from the .csv.
     */
    public static List<String[]> readCSV(String filePath) throws IOException {
        List<String[]> data = new ArrayList<>();

        try (BufferedReader csvReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = csvReader.readLine()) != null) {
                String[] values = line.split(",");
                data.add(values);
            }
        }
        return data;
    }

}
