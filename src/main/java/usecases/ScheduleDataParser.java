package usecases;

import java.util.ArrayList;
import java.util.List;

public class ScheduleDataParser {

    private final String KEYWORD = "SHIFT: ";
    private boolean shiftLine = true;
    private ArrayList<String[]> parsedData = new ArrayList<>();

    public ScheduleDataParser(List<String[]> rawData) {
        ArrayList<String> values = new ArrayList<>();

        for (String[] line : rawData) {
            if (shiftLine) {
                values.add(line[0].replace(KEYWORD, ""));
                shiftLine = false;
            } else {
                values.addAll(toArrayList(line));
                this.parsedData.add(this.toArray(values));
                values = new ArrayList<>();
                shiftLine = true;
            }
        }
    }

    public String[][] getParsedDataArray() {
        String[][] newLst = new String[this.parsedData.size()][];
        for (int i = 0; i < this.parsedData.size(); i++) {
            newLst[i] = this.parsedData.get(i);

            String[] currLst = newLst[i];
            currLst[0] = currLst[0].replaceAll("\"", "");
            for (int j = 1; j < currLst.length; j++) {
                currLst[j] = currLst[j].replace(" ", ", ");
                currLst[j] = currLst[j].replaceAll("\"", "");
            }
        }
        return newLst;
    }

    public ArrayList<String> toArrayList(String[] lst) {
        ArrayList<String> newLst = new ArrayList<>();
        for (String str : lst) {
            newLst.add(str);
        }
        return newLst;
    }

    public String[] toArray(ArrayList<String> lst) {
        String[] newLst = new String[lst.size()];
        for (int i = 0; i < lst.size(); i++) {
            newLst[i] = lst.get(i);
        }
        return newLst;
    }

}
