package Secondtry;

import org.junit.jupiter.api.Test;

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
}