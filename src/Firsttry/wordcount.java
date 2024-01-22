//Morris Tichy 4CN
/**
 * Dies ist die Dokumentation für die Klasse wordcount, die zur Zählung der Anzahl von Wörtern in einem gegebenen String dient.
 *
 * Die Klasse enthält eine Hauptmethode (main) zum Ausführen des Programms und eine öffentliche statische Methode zum Zählen der Wörter in einem String.
 */
package Firsttry;

public class wordcount {
    /**
     * Diese Methode zählt die Anzahl der Wörter in einem gegebenen String.
     *
     * @param s Der Eingabestring, in dem die Wörter gezählt werden sollen.
     * @return Die Anzahl der Wörter im Eingabestring. Ein Wort ist eine Folge von Buchstaben und kann durch Leerzeichen oder bestimmte Satzzeichen getrennt sein.
     */
    public static int count(String s){
        // Entfernt XML-Tags und bestimmte Satzzeichen, ersetzt sie durch Leerzeichen
        String s1 = s.replaceAll("(<[A-z \\\\=\"]+>?)|[:.\"]", " ");

        // Prüft, ob der Eingabestring leer oder nur aus Leerzeichen besteht
        if (s.isBlank() || s.isEmpty()){
            return 0;
        }

        int zaehler = 0;
        // Wenn der bereinigte String keine Leerzeichen enthält, handelt es sich um ein einzelnes Wort
        if (!s1.contains(" ")){
            return 1;
        }

        // Teilt den bereinigten String in Wörter und zählt sie
        String array [] = s1.split(" ");
        for (int i = 0; i < array.length; i++) {
            if (!array[i].isBlank()){
                zaehler++;
            }
        }
        return zaehler;
    }

    /**
     * Die Hauptmethode des Programms. Sie startet die Anwendung und kann verwendet werden, um die Wortanzahl in einem Eingabestring zu ermitteln.
     *
     * @param args Die Befehlszeilenargumente (nicht verwendet in diesem Programm).
     */
    public static void main(String[] args) {
        // Die Hauptmethode kann verwendet werden, um die Wortanzahl in einem Eingabestring zu ermitteln.
    }
}
