package usecases;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * ScheduleFileWriter.java
 * Class for ScheduleFileWriter.
 */
public class ScheduleFileWriter {

    /**
     * readCSV
     * Writes to the .csv at filePath given String[][] data.
     * @param data, String[][] representation of the Schedule data.
     * @param filePath, String for the file path of .csv to be written to.
     */
    public static void writeCSV(String[][] data, String filePath) throws IOException {
        FileWriter writer = new FileWriter(filePath);
        ArrayList<String[]> parsedData = parseForCSV(data);
        for (String[] row : parsedData) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < row.length; i++) {
                sb.append(row[i]);
                if (i < row.length - 1) {
                    sb.append(",");
                }
            }
            sb.append(System.lineSeparator());
            writer.write(sb.toString());
        }
        writer.close();
    }

    /**
     * parseForCSV
     * Parses the String[][] data to ArrayList<String[]> and adds proper formatting for writing to .csv.
     * @param data, String[][] representation of the Schedule data.
     * @return ArrayList<String[]> representation of teh Schedule data.
     */
    public static ArrayList<String[]> parseForCSV(String[][] data) {
        ArrayList<String[]> parsedData = new ArrayList<>();
        for (String[] lst : data) {
            String shiftText = "\"SHIFT: " + lst[0] + "\"";
            parsedData.add(new String[]{shiftText});
            String[] newLst = new String[lst.length - 1];
            for (int i = 1; i < lst.length; i++) {
                newLst[i - 1] = "\"" + lst[i].replaceAll(",", "") + "\"";
            }
            parsedData.add(newLst);
        }
        return parsedData;
    }

}