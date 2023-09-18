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
}