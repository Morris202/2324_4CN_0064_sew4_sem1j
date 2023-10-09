package ue03;

import Firsttry.wordcount;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CSVReaderTest {
    @Test
    public void testSplitWithComma() {
        String csvString = "John,Doe,30,New York";
        CSVReader reader = new CSVReader(csvString);
        String[] expected = {"John", "Doe", "30", "New York"};
        assertArrayEquals(expected, reader.split());
    }
}