package com.codecool.filepartreader;

import java.util.ArrayList;
import java.util.List;

public class FileWordAnalyzer {
    private FilePartReader reader;

    public FileWordAnalyzer(FilePartReader reader) {
        this.reader = reader;
    }

    public List<String> getWordsOrderedAlphabetically() {
        String text = reader.readLines();
        List<String> words = new ArrayList<>();
        // TODO: sort words
        return words;
    }

    public List<String> getWordsContainingSubstring(String substring) {
        String text = reader.readLines();
        List<String> words = new ArrayList<>();
        // TODO: filter words
        return words;
    }

    public List<String> getStringWhichPalindromes() {
        String text = reader.readLines();
        List<String> words = new ArrayList<>();
        // TODO: filter words
        return words;
    }
}
