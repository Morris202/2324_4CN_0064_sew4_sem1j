package ue02.Secondtry;

import javax.swing.plaf.nimbus.State;
import javax.swing.text.html.HTMLEditorKit;
/**
 * Die Klasse 'enum1' enthält eine Methode 'count', die dazu dient, die Anzahl der Wörter in einem Text zu zählen, wobei HTML-Tags und Escape-Sequenzen berücksichtigt werden. Die Methode verwendet einen endlichen Automaten (enum 'State') zur Zustandsverwaltung, um die verschiedenen Elemente im Text zu erkennen.
 *
 * Die Zustände des Automaten sind:
 * - INTAG: Innerhalb eines HTML-Tags.
 * - INWORD: Innerhalb eines Wortes.
 * - NOWORD: Außerhalb von Wörtern.
 * - INANF: Innerhalb eines Wertes in Anführungszeichen.
 * - INSLASH: Innerhalb einer Escape-Sequenz.
 *
 * Um die Anzahl der Wörter im Text zu zählen, durchläuft die Methode den Text Zeichen für Zeichen und analysiert den aktuellen Zustand, um festzustellen, ob sich das Zeichen in einem Wort oder außerhalb befindet. Die Methode ignoriert Leerzeichen und HTML-Tags und berücksichtigt Wörter innerhalb von Anführungszeichen und Escape-Sequenzen.
 *
 * @author [Morris Tichy]
 */
public class enum1 {
    int counter;


    enum State {
        INTAG,
        INWORD,
        NOWORD,
        INANF,
        INSLASH
    }

    public int count(String text) {
        /**
         * Zählt die Anzahl der Wörter im gegebenen Text.
         *
         * @param text Der Text, in dem die Wörter gezählt werden sollen.
         * @return Die Anzahl der Wörter im Text.
         */
        char c;
        State state = State.NOWORD;
        counter = 0;

        // Wenn der Text leer oder nur aus Leerzeichen besteht, wird 0 zurückgegeben.
        if (text.isEmpty() || text.isBlank()) {
            return 0;
        }

        for (int i = 0; i < text.length(); i++) {
            c = text.charAt(i);
            switch (state) {
                case NOWORD:
                    if (c == '<') {
                        state = State.INTAG;
                    } else if (Character.isLetter(c)) {
                        counter++;
                        state = State.INWORD;
                    }
                    break;
                case INWORD:
                    if (!Character.isLetter(c)){
                        state= State.NOWORD;
                    }
                    if (c == '<') {
                        state = State.INTAG;
                    }
                    break;
                case INTAG:
                    if (c == '>') {
                        state = State.NOWORD;
                    } else if (c == '\"') {
                        state = State.INANF;

                    }
                    break;
                case INANF:
                    if (c == '\"') {
                        state = State.INTAG;
                    } else if (c == '\\') {
                        state = State.INSLASH;

                    }
                    break;
                case INSLASH:
                    if (c == '\"') {
                        state = State.INANF;
                    }
                    break;
            }

            System.out.printf(String.valueOf(state + " "));
        }
        return counter;
    }
}
