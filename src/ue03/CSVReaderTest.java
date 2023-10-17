package ue03;

import Firsttry.wordcount;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CSVReaderTest {
    @Test
    public void testSplitWithComma() {
        String csvString = "John,Doe,New York";
        CSVReader reader = new CSVReader(csvString);
        List<String> expected = List.of("John", "Doe", "New York");
        List<String> result = reader.split(csvString);
        assertEquals(expected, result);
    }
    @Test
    public void testSplitWithSemicolonSeparator() {
        String csvString = "Alice,Smith,25,Los Angeles";
        CSVReader reader = new CSVReader(csvString);
        List<String> expected = List.of("Alice", "Smith","25","Los Angeles");
        List<String> result = reader.split(csvString);
        assertEquals(expected, result);
    }
    @Test
    public void testValidCSVFields() {
        String csvString = "\"ok\",\"ok, ok\",\"nicht\"ok,\"nicht ok\"";
        CSVReader reader = new CSVReader(csvString);
        List<String> expected = List.of("ok", "ok, ok", "nicht\"ok", "nicht ok");
        List<String> result = reader.split(csvString);
        assertEquals(expected, result);
    }
}