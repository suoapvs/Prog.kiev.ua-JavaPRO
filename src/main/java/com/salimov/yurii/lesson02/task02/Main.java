package com.salimov.yurii.lesson02.task02;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

/**
 * 2.2 Распарсить следующую структуру данных:
 * {
 *      "name": "...",
 *      "surname": "...",
 *      "phones": ["044-256-78-90", "066-123-45-67", "..."],
 *      "sites": ["http://site1.com", "http://site2.com", "..."],
 *      "address": {
 *          "country": "...",
 *          "city": "...",
 *          "street": "..."
 *      }
 * }
 *
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 */
public class Main {

    public static void main(String[] args) {
        try {
            final String path = "json.txt";
            final String jsonStr = readFile(path);
            final String result = parseJson(jsonStr, JSON.class);
            System.out.println(result);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static String readFile(final String path) throws IOException {
       final Reader<String> reader = new FilesReader(path);
       return reader.read();
    }

    private static <T extends JSON> String parseJson(final String jsonStr, final Class<T> cls) {
        final Gson gson = new GsonBuilder().create();
        final JSON json = gson.fromJson(jsonStr, cls);
        return json.toString();
    }
}
