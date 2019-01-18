package com.codecool.filepartreader;

import org.junit.jupiter.api.Test;
import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class FilePartReaderTest {
    private static final String SHORT_TEST_FILE = "test_short_textfile.txt";
    private static final String LONG_TEST_FILE = "test_textfile.txt";
    private static final String NONEXISTENT_FILE = "this_file_does_not_exist.txt";
    private FilePartReader testReader;

    @Test
    public void testSetupThrowsIllegalArgumentsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new FilePartReader().setup(LONG_TEST_FILE, 4, 3);
        });
    }

    void setupTestReader(String filePath, int fromLine, int toLine) {
        FilePartReader testReader = new FilePartReader();
        testReader.setup(filePath, fromLine, toLine);
        this.testReader = testReader;
    }

    @Test
    public void testReadKeepsNewLines() {
        setupTestReader(SHORT_TEST_FILE, 1, 5);
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

    @Test
    public void testReadThrowsFileNotFoundException() {
        setupTestReader(NONEXISTENT_FILE, 1, 5);
        assertThrows(FileNotFoundException.class, () -> {
            testReader.read();
        });
    }

    @Test
    public void testReadLinesCatchesFileNotFoundException() {
        setupTestReader(NONEXISTENT_FILE, 1, 2);
        String expectedResult = NONEXISTENT_FILE + " (Nincs ilyen fájl vagy könyvtár)";
        String actualResult = testReader.readLines();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testReadLinesProperResultShort() {
        setupTestReader(SHORT_TEST_FILE, 2, 4);
        String expectedResult = "I wished I knew what god’s name to say.\n" +
                "Finally, I made a silent plea. Whoever you are, tell me. Please.\n" +
                "I scraped a big slice of brisket into the flames.";
        String actualResult = testReader.readLines();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testReadLinesProperResultLong() {
        setupTestReader(LONG_TEST_FILE, 78, 88);
        String expectedResult = "The blond girl I’d met at the Big House was reading a book in front of the last cabin on the left, number eleven.\n" +
                "When we reached her, she looked me over critically, like she was still thinking about how much I drooled.\n" +
                "I tried to see what she was reading, but I couldn’t make out the title. I thought my dyslexia was acting up. Then I realized the title wasn’t even English. The letters looked Greek to me. I mean, literally Greek. There were pictures of temples and statues and different kinds of columns, like those in an architecture book.\n" +
                "“Annabeth,” Chiron said, “I have masters’ archery class at noon. Would you take Percy from here?”\n" +
                "“Yes, sir.”\n" +
                "“Cabin eleven,” Chiron told me, gesturing toward the doorway. “Make yourself at home.”\n" +
                "Out of all the cabins, eleven looked the most like a regular old summer camp cabin, with the emphasis on old. The threshold was worn down, the brown paint peeling. Over the doorway was one of those doctor’s symbols, a winged pole with two snakes wrapped around it. What did they call it… ? A caduceus.\n" +
                "Inside, it was packed with people, both boys and girls, way more than the number of bunk beds. Sleeping bags were spread all over on the floor. It looked like a gym where the Red Cross had set up an evacuation center.\n" +
                "Chiron didn’t go in. The door was too low for him. But when the campers saw him they all stood and bowed respectfully.\n" +
                "“Well, then,” Chiron said. “Good luck, Percy. I’ll see you at dinner.”\n" +
                "He galloped away toward the archery range.";
        String actualResult = testReader.readLines();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testReadLinesFromFirst() {
        setupTestReader(LONG_TEST_FILE, 1, 6);
        String expectedResult = "6 - I BECOME SUPREME LORD OF THE BATHROOM\n" +
                "Once I got over the fact that my Latin teacher was a horse, we had a nice tour, though I was careful not to walk behind him. I’d done pooper-scooper patrol in the Macy’s Thanksgiving Day Parade a few times, and, I’m sorry, I did not trust Chiron’s back end the way I trusted his front.\n" +
                "We passed the volleyball pit. Several of the campers nudged each other. One pointed to the minotaur horn I was carrying. Another said, “That’s him.”\n" +
                "Most of the campers were older than me. Their satyr friends were bigger than Grover, all of them trotting around in orange CAMP HALF-BLOOD T-shirts, with nothing else to cover their bare shaggy hindquarters. I wasn’t normally shy, but the way they stared at me made me uncomfortable. I felt like they were expecting me to do a flip or something.\n" +
                "I looked back at the farmhouse. It was a lot bigger than I’d realized-four stories tall, sky blue with white trim, like an upscale seaside resort. I was checking out the brass eagle weather vane on top when something caught my eye, a shadow in the uppermost window of the attic gable. Something had moved the curtain, just for a second, and I got the distinct impression I was being watched.\n" +
                "“What’s up there?” I asked Chiron.";
        String actualResult = testReader.readLines();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testReadLinesTillLast() {
        setupTestReader(SHORT_TEST_FILE, 4, 6);
        String expectedResult = "I scraped a big slice of brisket into the flames.\n" +
                "When I caught a whiff of the smoke, I didn’t gag.\n" +
                "It smelled nothing like burning food. It smelled of hot chocolate and fresh-baked brownies, hamburgers on the grill and wildflowers, and a hundred other good things that shouldn’t have gone well together, but did.";
        String actualResult = testReader.readLines();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testToLineOutOfBounds() {
        setupTestReader(LONG_TEST_FILE, 179, 200);
        String expectedResult = "She struggled, gasping, and her friends started coming toward her. But then the other toilets exploded, too, and six more streams of toilet water blasted them back. The showers acted up, too, and together all the fixtures sprayed the camouflage girls right out of the bathroom, spinning them around like pieces of garbage being washed away.\n" +
                "As soon as they were out the door, I felt the tug in my gut lessen, and the water shut off as quickly as it had started.\n" +
                "The entire bathroom was flooded. Annabeth hadn’t been spared. She was dripping wet, but she hadn’t been pushed out the door. She was standing in exactly the same place, staring at me in shock.\n" +
                "I looked down and realized I was sitting in the only dry spot in the whole room. There was a circle of dry floor around me. I didn’t have one drop of water on my clothes. Nothing.\n" +
                "I stood up, my legs shaky.\n" +
                "Annabeth said, “How did you …”\n" +
                "“I don’t know.”\n" +
                "We walked to the door. Outside, Clarisse and her friends were sprawled in the mud, and a bunch of other campers had gathered around to gawk. Clarisse’s hair was flattened across her face. Her camouflage jacket was sopping and she smelled like sewage. She gave me a look of absolute hatred. “You are dead, new boy. You are totally dead.”\n" +
                "I probably should have let it go, but I said, “You want to gargle with toilet water again, Clarisse? Close your mouth.”\n" +
                "Her friends had to hold her back. They dragged her toward cabin five, while the other campers made way to avoid her flailing feet.\n" +
                "Annabeth stared at me. I couldn’t tell whether she was just grossed out or angry at me for dousing her.\n" +
                "“What?” I demanded. “What are you thinking?”\n" +
                "“I’m thinking,” she said, “that I want you on my team for capture the flag.”";
        String actualResult = testReader.readLines();
        assertEquals(expectedResult, actualResult);
    }

}