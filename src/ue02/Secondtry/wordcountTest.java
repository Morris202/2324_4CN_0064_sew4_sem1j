package ue02.Secondtry;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class wordcountTest {
    @Test
    void easy () {
        wordcount easy = new wordcount();
        assertEquals(0, easy.count(""));
        assertEquals(0, easy.count(" "));
        assertEquals(0, easy.count("   "));
    }
    @Test
    void normal(){
        wordcount normal = new wordcount();
        assertEquals(1, normal.count("eins"));
        assertEquals(1, normal.count(" eins"));
        assertEquals(1, normal.count("eins "));
        assertEquals(1, normal.count(" eins "));
        assertEquals(1, normal.count(" eins  "));
        assertEquals(1, normal.count("  eins "));
        assertEquals(1, normal.count("  eins  "));
        assertEquals(1, normal.count("eins:"));
        assertEquals(1, normal.count(":eins"));
        assertEquals(1, normal.count(":eins:"));
        assertEquals(1, normal.count(" eins  "));
        assertEquals(1, normal.count(" eins : "));
        assertEquals(1, normal.count(": eins :"));
        assertEquals(3, normal.count("ein erster Text"));
        assertEquals(3, normal.count(" ein  erster   Text"));
        assertEquals(3, normal.count("ein:erster.Text"));
    }
    @Test
    void vielleichtfalsch(){
        wordcount vielleichtfalsch = new wordcount();
        assertEquals(1, vielleichtfalsch.count("a"));
        assertEquals(1, vielleichtfalsch.count(" a"));
        assertEquals(1, vielleichtfalsch.count("a "));
        assertEquals(1, vielleichtfalsch.count(" a "));
    }
    @Test
    void mithtml(){
        wordcount mithtml = new wordcount();
        assertEquals(1, mithtml.count(" eins  <html> "));
        assertEquals(1, mithtml.count(" eins  < html> "));
        assertEquals(1, mithtml.count(" eins  <html > "));
        assertEquals(1, mithtml.count(" eins  < html > "));
        assertEquals(4, mithtml.count(" eins <html> zwei<html>drei <html> vier"));
        assertEquals(2, mithtml.count(" eins <html> zwei "));
        assertEquals(2, mithtml.count(" eins <html>zwei "));
        assertEquals(2, mithtml.count(" eins<html> zwei "));
        assertEquals(2, mithtml.count(" eins<html>zwei "));
        assertEquals(2, mithtml.count(" eins<img alt=xxx > zwei"));
        assertEquals(2, mithtml.count(" eins<img alt=xxx yyy > zwei"));
        assertEquals(2, mithtml.count(" eins \"zwei\" "));
        assertEquals(2, mithtml.count(" eins\"zwei\" "));
        assertEquals(2, mithtml.count(" eins \"zwei\""));
        assertEquals(3, mithtml.count(" eins \"zwei\"drei"));
        assertEquals(3, mithtml.count(" eins \"zwei\" drei"));
    }
    @Test
    void htmltrickreich(){
        wordcount htmltrickreich = new wordcount();
        assertEquals(1, htmltrickreich.count(" eins<html"));
        assertEquals(2, htmltrickreich.count(" eins<img alt=\"<bild>\" > zwei"));
        assertEquals(2, htmltrickreich.count(" eins<img alt=\"bild>\" > zwei"));
        assertEquals(2, htmltrickreich.count(" eins<img alt=\"<bild>\" keinwort> zwei"));
        assertEquals(2, htmltrickreich.count(" eins<img alt=\"<bild>\" src=\"bild.png\" >zwei"));
        assertEquals(2, htmltrickreich.count(" eins<img alt=\"<bild\" keinwort>zwei"));
        assertEquals(1, htmltrickreich.count(" eins<img alt=\"<bild\" keinwort"));
        assertEquals(2, htmltrickreich.count(" eins<img alt=\"<bild\" keinwort> zwei"));
        assertEquals(1, htmltrickreich.count(" eins<img alt=\"<bild keinwort> keinwort"));
        assertEquals(2, htmltrickreich.count(" eins<img alt=\"<bild keinwort keinwort\">zwei"));
        assertEquals(2, htmltrickreich.count(" eins<img alt=\"<bild keinwort< keinwort\">zwei"));

// ganz ganz fies -- \ entwertet das nächste Zeichen
        assertEquals(2, htmltrickreich.count(" eins<img alt=\"<bild \\\" keinwort> keinwort\" keinwort>zwei"));
        assertEquals(2, htmltrickreich.count(" eins<img alt=\"<bild \\\" keinwort<keinwort\" keinwort>zwei"));
        assertEquals(2, htmltrickreich.count(" eins<img alt=\"<bild \\\" keinwort keinwort\" keinwort>zwei"));
        assertEquals(4, htmltrickreich.count(" \\\"null\\\" eins<img alt=\"<bild \\\" keinwort keinwort\" keinwort>zwei \"drei\""));

    }

    public static void main(String[] args) {

        try {
            String filePath = "C:\\Users\\morri\\Downloads\\crsto12.html";

            wordcount wordCounter = new wordcount();

            // Open the file
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            StringBuilder stringBuilder = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }

            // Close the file
            reader.close();

            // Get the entire file content as a String
            String fileContent = stringBuilder.toString();

            // Count words using the WordCount class
            int wordCount = wordCounter.count(fileContent);
            System.out.println("Word count: " + wordCount);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @Test
    void readFile() {
        wordcount file = new wordcount();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\morri\\Downloads\\crsto12.html"));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            reader.close();
            String fileContent = stringBuilder.toString();
            System.out.println(file.count(fileContent));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}