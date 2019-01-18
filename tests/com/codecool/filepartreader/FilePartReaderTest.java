package com.codecool.filepartreader;

import org.junit.jupiter.api.Test;
import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class FilePartReaderTest {
    private FilePartReader testReader;

    void setupTestReader(String filePath, int fromLine, int toLine) {
        FilePartReader testReader = new FilePartReader();
        testReader.setup(filePath, fromLine, toLine);
        this.testReader = testReader;
    }

    @Test
    public void testReadKeepsNewLines() {
        setupTestReader("test_short_textfile.txt", 1, 5);
        String expectedResult = "I was next.\n" +
                "I wished I knew what god’s name to say.\n" +
                "Finally, I made a silent plea. Whoever you are, tell me. Please.\n" +
                "I scraped a big slice of brisket into the flames.\n" +
                "When I caught a whiff of the smoke, I didn’t gag.\n" +
                "It smelled nothing like burning food. It smelled of hot chocolate and fresh-baked brownies, hamburgers on the grill and wildflowers, and a hundred other good things that shouldn’t have gone well together, but did.";
        String actualResult = null;
        try {
            actualResult = testReader.read();
        } catch (FileNotFoundException e) {
            actualResult = e.getMessage();
        }
        assertEquals(expectedResult, actualResult);
    }

}