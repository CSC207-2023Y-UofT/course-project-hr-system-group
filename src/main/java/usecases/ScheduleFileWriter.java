package usecases;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ScheduleFileWriter {

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