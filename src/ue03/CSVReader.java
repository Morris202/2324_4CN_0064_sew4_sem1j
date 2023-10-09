package ue03;

public class CSVReader {
     String csvstring;

    public CSVReader(String csvstring) {
        this.csvstring = csvstring;
    }

    public String[] split() {
        return this.csvstring.split(",");
    }
}
