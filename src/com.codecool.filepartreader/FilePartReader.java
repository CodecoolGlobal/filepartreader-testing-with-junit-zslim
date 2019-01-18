package com.codecool.filepartreader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringJoiner;

class FilePartReader {
    private String filePath;
    private Integer fromLine;
    private Integer toLine;

    public FilePartReader() {
        this.filePath = "";
        this.fromLine = 0;
        this.toLine = 0;
    }

    public void setup(String filePath, Integer fromLine, Integer toLine) throws IllegalArgumentException {

        if (toLine < fromLine || fromLine < 1) {
            throw new IllegalArgumentException("toLine must be larger than fromLine");
        }

        this.filePath = filePath;
        this.fromLine = fromLine;
        this.toLine = toLine;
    }

    public String read() throws FileNotFoundException {
        File file = new File(filePath);
        Scanner sc = new Scanner(file);
        StringJoiner sj = new StringJoiner("\n");
        while (sc.hasNextLine()) {
            sj.add(sc.nextLine());
        }
        return sj.toString();
    }

    public String readLines() {
        try {
            String wholeFile = read();
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        StringBuilder sb = new StringBuilder();
        // TODO: iterate through lines and get the appropriate ones

        return sb.toString();
    }
}