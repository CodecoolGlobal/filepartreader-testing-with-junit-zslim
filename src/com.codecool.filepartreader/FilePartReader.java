package com.codecool.filepartreader;

class FilePartReader {
    private String filePath;
    private Integer fromLine;
    private Integer toLine;

    public FilePartReader() {
        this.filePath = "";
        this.fromLine = 0;
        this.toLine = 0;
    }

    public void setup(String filePath, Integer fromLine, Integer toLine) {

        if (toLine < fromLine || fromLine < 1) {
            throw IllegalArgumentException; // TODO: how to properly throw exceptions?
        }

        this.filePath = filePath;
        this.fromLine = fromLine;
        this.toLine = toLine;
    }

    public String read() {
        // TODO: look up how to handle files
        String text = "";
        return text;
    }

    public String readLines() {
        String wholeFile = read();

        StringBuilder sb = new StringBuilder();
        // TODO: iterate through lines and get the appropriate ones

        return sb.toString();
    }
}