package ue03;

import java.util.ArrayList;
import java.util.List;

/**
 * CSVReader ist eine Klasse zum Parsen von CSV-Zeichenketten und zum Aufteilen in Spalten.
 */
public class CSVReaderV2 {

    // Liste zum Speichern der Ergebnisse (Spalten)
    List<String> erg = new ArrayList<>();

    // Aktuelle Zeichenkette für die aktuelle Spalte
    String akt = "";

    // Vorheriger Zustand während des Parsens
    State previState;

    // Zähler für Anführungszeichen in einer Zeichenkette
    int StringTag;

    /**
     * Enum für verschiedene Zustände während des Parsens.
     */
    enum State {
        /**
         * Zustand für die Verarbeitung von regulären Spalten.
         */
        Coloumn {
            /**
             * Verarbeitet das aktuelle Zeichen und gibt den neuen Zustand zurück.
             *
             * @param c       Das aktuelle Zeichen
             * @param context Die Instanz des CSVReader, die den Zustand verwaltet
             * @return Der neue Zustand
             * @throws IllegalArgumentException Wenn ein ungültiges Zeichen in einer Zeichenkette gefunden wird
             */
            State handleChar(char c, CSVReaderV2 context) {
                if (Character.isWhitespace(c)) {
                    return whitespace;
                } else if (Character.isLetter(c) && context.previState == StringTag) {
                    throw new IllegalArgumentException("Ungültiges Zeichen in Zeichenkette gefunden");
                } else if (c == ',') {
                    context.erg.add(context.akt);
                    context.akt = "";
                    context.previState = Coloumn;
                    context.StringTag = 0;
                    return NoColoum;
                } else if (c == '\"' && context.StringTag > 0) {
                    context.akt += '\"';
                    return StringTag;
                } else if (c == '\"') {
                    context.previState = Coloumn;
                    return StringTag;
                }
                context.akt += c;
                context.previState = Coloumn;
                return Coloumn;
            }
        },

        /**
         * Zustand, wenn keine aktuelle Spalte vorhanden ist.
         */
        NoColoum {
            /**
             * Verarbeitet das aktuelle Zeichen und gibt den neuen Zustand zurück.
             *
             * @param c       Das aktuelle Zeichen
             * @param context Die Instanz des CSVReader, die den Zustand verwaltet
             * @return Der neue Zustand
             */
            State handleChar(char c, CSVReaderV2 context) {
                if (c == ',') {
                    context.akt += "";
                    context.erg.add(context.akt);
                    return NoColoum;
                } else if (c == '\"') {
                    context.previState = NoColoum;
                    return StringTag;
                }
                context.akt += c;
                return Coloumn;
            }
        },

        /**
         * Zustand für die Verarbeitung von Zeichenketten.
         */
        StringTag {
            /**
             * Verarbeitet das aktuelle Zeichen und gibt den neuen Zustand zurück.
             *
             * @param c       Das aktuelle Zeichen
             * @param context Die Instanz des CSVReader, die den Zustand verwaltet
             * @return Der neue Zustand
             */
            State handleChar(char c, CSVReaderV2 context) {
                if (c == '\"') {
                    context.previState = StringTag;
                    context.StringTag++;
                    System.out.println(context.StringTag);
                    return Coloumn;
                }
                context.akt += c;
                return StringTag;
            }
        },

        /**
         * Zustand für die Verarbeitung von Leerzeichen.
         */
        whitespace {
            /**
             * Verarbeitet das aktuelle Zeichen und gibt den neuen Zustand zurück.
             *
             * @param c       Das aktuelle Zeichen
             * @param context Die Instanz des CSVReader, die den Zustand verwaltet
             * @return Der neue Zustand
             */
            @Override
            State handleChar(char c, CSVReaderV2 context) {
                if (Character.isLetter(c)) {
                    return Coloumn;
                } else if (c == '\"') {
                    return StringTag;
                } else if (c == ',') {
                    return NoColoum;
                }
                return whitespace;
            }
        };

        /**
         * Abstrakte Methode zum Verarbeiten des aktuellen Zeichens und Übergang zum neuen Zustand.
         *
         * @param c       Das aktuelle Zeichen
         * @param context Die Instanz des CSVReader, die den Zustand verwaltet
         * @return Der neue Zustand
         */
        abstract State handleChar(char c, CSVReaderV2 context);
    }

    /**
     * Teilt die übergebene Zeichenkette in Spalten auf.
     *
     * @param text Die zu verarbeitende CSV-Zeichenkette
     * @return Eine Liste von Spalten (Strings)
     */
    public List<String> split(String text) {
        // Initialer Zustand ist 'Coloumn'
        State state = State.Coloumn;
        // Liste für Ergebnisse zurücksetzen
        erg = new ArrayList<>();
        // Durchlaufe alle Zeichen in der Zeichenkette
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            // Verarbeite das Zeichen basierend auf dem aktuellen Zustand
            state = state.handleChar(c, this);
            System.out.println(state + " ");
            // Überprüfe, ob es das letzte Zeichen ist und die aktuelle Spalte beendet werden muss
            if (i == text.length() - 1 && c == ',') {
                akt += "";
                erg.add(akt);
            } else if (i == text.length() - 1) {
                erg.add(akt);
                akt = "";
            }
            System.out.print(c + " state");
        }
        return erg;
    }
}
