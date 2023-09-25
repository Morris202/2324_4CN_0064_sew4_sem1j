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
}