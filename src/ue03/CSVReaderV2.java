package ue03;

import java.util.ArrayList;
import java.util.List;

/**
 __author__ = Morris TIchy
 */
public class CSVReaderV2 {

    List<String> columns = new ArrayList<>();

    String currentColumn = "";

    State prevState;
    int quoteCounter;

    /**
     * Enum für verschiedene Zustände während des Parsens.
     */
    enum State {
        /**
         * Zustand für die Verarbeitung von regulären Spalten.
         */
        COLUMN {
            public State quoteCounter;




            /**
             * @param c       previous char
             * @param context CSVReader, which shows the state
             * @return new State
             * @throws IllegalArgumentException if a unknown symbol
             */
            State handleChar(char c, CSVReaderV2 context) {
                if (Character.isWhitespace(c)) {
                    return WHITESPACE;
                } else if (Character.isLetter(c) && context.prevState == quoteCounter) {
                    throw new IllegalArgumentException("unknown symbol");
                } else if (c == ',') {
                    context.columns.add(context.currentColumn);
                    context.currentColumn = "";
                    context.prevState = COLUMN;
                    context.quoteCounter = 0;
                    return NO_COLUMN;
                } else if (c == '\"' && context.quoteCounter > 0) {
                    context.currentColumn += '\"';
                    return QUOTE;
                } else if (c == '\"') {
                    context.prevState = COLUMN;
                    return QUOTE;
                }
                context.currentColumn += c;
                context.prevState = COLUMN;
                return COLUMN;
            }
        },
        NO_COLUMN {
            State handleChar(char c, CSVReaderV2 context) {
                if (c == ',') {
                    context.currentColumn += "";
                    context.columns.add(context.currentColumn);
                    return NO_COLUMN;
                } else if (c == '\"') {
                    context.prevState = NO_COLUMN;
                    return QUOTE;
                }
                context.currentColumn += c;
                return COLUMN;
            }
        },
        QUOTE {

            State handleChar(char c, CSVReaderV2 context) {
                if (c == '\"') {
                    context.prevState = QUOTE;
                    context.quoteCounter++;
                    System.out.println(context.quoteCounter);
                    return COLUMN;
                }
                context.currentColumn += c;
                return QUOTE;
            }
        },
        WHITESPACE {
            @Override
            State handleChar(char c, CSVReaderV2 context) {
                if (Character.isLetter(c)) {
                    return COLUMN;
                } else if (c == '\"') {
                    return QUOTE;
                } else if (c == ',') {
                    return NO_COLUMN;
                }
                return WHITESPACE;
            }
        };
        abstract State handleChar(char c, CSVReaderV2 context);
    }

    /**
     * @param text finished csv-file
     * @return a list
     */
    public List<String> split(String text) {
        State state = State.COLUMN;
        columns = new ArrayList<>();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            state = state.handleChar(c, this);
            if (i == text.length() - 1 && c == ',') {
                currentColumn += "";
                columns.add(currentColumn);
            } else if (i == text.length() - 1) {
                columns.add(currentColumn);
                currentColumn = "";
            }
        }
        return columns;
    }
}
