package com.salimov.yurii.lesson01.task02;

import java.io.FileWriter;
import java.io.IOException;

@SaveTo(path = "c:\\file.txt")
public final class TextContainer {

    private final String text;

    public TextContainer(final String text) {
        this.text = text;
    }

    @Saver
    public void save(final String path) throws IOException {
        try (FileWriter writer = new FileWriter(path)) {
            writer.write(this.text);
        }
    }
}
