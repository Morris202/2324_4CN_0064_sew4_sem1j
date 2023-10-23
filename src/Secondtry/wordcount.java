package Secondtry;

import javax.swing.plaf.nimbus.State;
import java.io.IOException;
/**
 * Die Klasse "wordcount" dient dazu, die Anzahl der Wörter in einem gegebenen Text zu zählen, wobei die Textverarbeitung von einem endlichen Automaten mit verschiedenen Zuständen gesteuert wird.
 */
public class wordcount {
    /**
     * Der Zähler, der die Anzahl der Wörter im Text speichert.
     */
    int counter;
    enum State {
        /**
         * Enum "State" stellt die verschiedenen Zustände des endlichen Automaten dar, der die Textverarbeitung steuert.
         */
        NOWORD{
            /**
             * Behandelt das gegebene Zeichen im Zustand "NOWORD" und ändert ggf. den Zustand.
             *
             * @param c       Das Zeichen, das behandelt wird.
             * @param context Das "wordcount"-Objekt, in dem der Zähler gespeichert ist.
             * @return Der neue Zustand des Automaten.
             */
            State handleChar(char c, wordcount context) {
                if (Character.isLetter(c)) {
                    context.counter++;
                    return INWORD;
                } else if (c== '<'){
                    return InTag;
                }
                else {
                    return NOWORD;
                }
            }
        },
        // Andere Zustände und deren Dokumentation hier ...

        /**
         * Abstrakte Methode, die von jedem Zustand implementiert werden muss, um die Zeichenverarbeitung zu steuern.
         *
         * @param c       Das Zeichen, das behandelt wird.
         * @param context Das "wordcount"-Objekt, in dem der Zähler gespeichert ist.
         * @return Der neue Zustand des Automaten.
         */

        INWORD {
            State handleChar(char c, wordcount context) {
                if (c == ':'|| c== '.' || c == '\\' || c == ' ' || c == '\"') {
                    return NOWORD;

              }  else if(c == '<'){
                    return InTag;
                }
                else {
                    return INWORD;
                }
            }
        },
        InTag{
            State handleChar(char c, wordcount context) {
                if (c == '>') {
                    return NOWORD;
               }
                else if (c == '\"'){
                    return INANF;
                }

                else {
                    return InTag;
                }
            }
       },
        INANF{
            State handleChar(char c, wordcount context) {
                if (c == '\"') {
                    return InTag;
                }
                else if (c == '\\'){
                    return INSLASH;
                }
                else {
                    return INANF;
                }
            }
        },
        INSLASH{
            State handleChar(char c, wordcount context) {
                if (c == '\"') {
                    return INANF;
                }
                else {
                    return INSLASH;
                }
            }
        };
        abstract State handleChar(char c, wordcount context);
        /**
         * Diese Methode zählt die Anzahl der Wörter im gegebenen Text mithilfe des endlichen Automaten.
         *
         * @param text Der Eingabetext, in dem die Wörter gezählt werden sollen.
         * @return Die Anzahl der Wörter im Eingabetext.
         */

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

