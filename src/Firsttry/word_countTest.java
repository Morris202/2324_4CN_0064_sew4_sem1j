package Firsttry;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class word_countTest {
    @Test
     void easy () {
        // leicht
        // leicht
        assertEquals(0, wordcount.count(""));
        assertEquals(0, wordcount.count(" "));
        assertEquals(0, wordcount.count("   "));
    }
    @Test
    void normal(){
        assertEquals(1, wordcount.count("eins"));
        assertEquals(1, wordcount.count(" eins"));
        assertEquals(1, wordcount.count("eins "));
        assertEquals(1, wordcount.count(" eins "));
        assertEquals(1, wordcount.count(" eins  "));
        assertEquals(1, wordcount.count("  eins "));
        assertEquals(1, wordcount.count("  eins  "));
        assertEquals(1, wordcount.count("eins:"));
        assertEquals(1, wordcount.count(":eins"));
        assertEquals(1, wordcount.count(":eins:"));
        assertEquals(1, wordcount.count(" eins  "));
        assertEquals(1, wordcount.count(" eins : "));
        assertEquals(1, wordcount.count(": eins :"));
        assertEquals(3, wordcount.count("ein erster Text"));
        assertEquals(3, wordcount.count(" ein  erster   Text"));
        assertEquals(3, wordcount.count("ein:erster.Text"));
    }
    @Test
    void vielleichtfalsch(){
        assertEquals(1, wordcount.count("a"));
        assertEquals(1, wordcount.count(" a"));
        assertEquals(1, wordcount.count("a "));
        assertEquals(1, wordcount.count(" a "));
    }
    @Test
    void mithtml(){
        assertEquals(1, wordcount.count(" eins  <html> "));
        assertEquals(1, wordcount.count(" eins  < html> "));
        assertEquals(1, wordcount.count(" eins  <html > "));
        assertEquals(1, wordcount.count(" eins  < html > "));
        assertEquals(4, wordcount.count(" eins <html> zwei<html>drei <html> vier"));
        assertEquals(2, wordcount.count(" eins <html> zwei "));
        assertEquals(2, wordcount.count(" eins <html>zwei "));
        assertEquals(2, wordcount.count(" eins<html> zwei "));
        assertEquals(2, wordcount.count(" eins<html>zwei "));
        assertEquals(2, wordcount.count(" eins<img alt=xxx > zwei"));
        assertEquals(2, wordcount.count(" eins<img alt=xxx yyy > zwei"));
        assertEquals(2, wordcount.count(" eins \"zwei\" "));
        assertEquals(2, wordcount.count(" eins\"zwei\" "));
        assertEquals(2, wordcount.count(" eins \"zwei\""));
        assertEquals(3, wordcount.count(" eins \"zwei\"drei"));
        assertEquals(3, wordcount.count(" eins \"zwei\" drei"));
    }
   /* @Test
    void htmltrickreich(){
        assertEquals(1, wordcount.count(" eins<html"));
        assertEquals(2, wordcount.count(" eins<img alt=\"<bild>\" > zwei"));
        assertEquals(2, wordcount.count(" eins<img alt=\"bild>\" > zwei"));
        assertEquals(2, wordcount.count(" eins<img alt=\"<bild>\" keinwort> zwei"));
        assertEquals(2, wordcount.count(" eins<img alt=\"<bild>\" src=\"bild.png\" >zwei"));
        assertEquals(2, wordcount.count(" eins<img alt=\"<bild\" keinwort>zwei"));
        assertEquals(1, wordcount.count(" eins<img alt=\"<bild\" keinwort"));
        assertEquals(2, wordcount.count(" eins<img alt=\"<bild\" keinwort> zwei"));
        assertEquals(1, wordcount.count(" eins<img alt=\"<bild keinwort> keinwort"));
        assertEquals(2, wordcount.count(" eins<img alt=\"<bild keinwort keinwort\">zwei"));
        assertEquals(2, wordcount.count(" eins<img alt=\"<bild keinwort< keinwort\">zwei"));



    }
    */
}