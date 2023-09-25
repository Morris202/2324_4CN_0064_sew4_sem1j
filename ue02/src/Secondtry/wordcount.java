package Secondtry;

import javax.swing.plaf.nimbus.State;

public class wordcount {
    int counter;
    enum State {

        NOWORD{
            State handleChar(char c, wordcount context) {
                if (Character.isLetter(c)) {
                    context.counter++;
                    return INWORD;
                } else {
                    return NOWORD; // oder: return this;
                }
            }
        },

        INWORD {
            State handleChar(char c, wordcount context) {
                if (c == ':'|| c== '.' || c == '\\' || c == ' ') {
                    return NOWORD;

              /*  }  else if(c == '<'){
                    return InTag;
               */ }
                else {
                    return INWORD; // oder: return this;
                }
            }
        };
/*
        InAnf{

        },
        InTag{

       };        */
        abstract State handleChar(char c, wordcount context);


    }
    public int count(String text) {
        State state = State.NOWORD;
        counter = 0;
        for (char c : text.toCharArray()) {
            state = state.handleChar(c, this);
        }
        return counter;
    }
}
