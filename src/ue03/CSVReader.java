package ue03;

import java.util.ArrayList;
import java.util.List;

public class CSVReader {
    String csvstring;
    List<String>results = new ArrayList<>();

    String puffer = "";
     enum State {
         NOWORD{
             State handleChar(char c, CSVReader reader) {
                 if (Character.isLetter(c) || Character.isDigit(c) || Character.isSpaceChar(c)) {
                     reader.results.add(reader.puffer);
                     reader.puffer = "";
                     reader.puffer+=c;
                     return INWORD;
                 }
                 else {
                     return NOWORD;
                 }
             }
         },
         INWORD {
             State handleChar(char c, CSVReader reader) {
                 if (c == ',') {
                     return NOWORD;
                 }
                 else {
                     reader.puffer+=c;
                     return INWORD;

                 }
             }
         };
         abstract State handleChar(char c, CSVReader reader);
    }

    public CSVReader(String csvstring) {
        this.csvstring = csvstring;
    }
    public List<String> split(String text) {
        State state = State.INWORD;
        results = new ArrayList<>();
        for (char c : text.toCharArray()) {
            state = state.handleChar(c, this);
            System.out.println(c);
        }
        if (state == state.INWORD){
            results.add(puffer);
        }
        return results;
    }



}
