package com.salimov.yurii.lesson02.task02;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public final class FilesReader implements Reader<String> {

    private final String path;

    public FilesReader(final String path) {
        this.path = path;
    }

    @Override
    public String read() throws IOException {
        try (FileReader fr = new FileReader(this.path);
             BufferedReader reader = new BufferedReader(fr)) {
            final StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
            return sb.toString();
        }
    }
}
