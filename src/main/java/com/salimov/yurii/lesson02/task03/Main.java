package com.salimov.yurii.lesson02.task03;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 2.3 Написать парсер для Yahoo Finance в режиме XML (format = xml).
 *
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 */
public class Main {

    public static void main(String[] args) throws Exception {
        final String request = "http://query.yahooapis.com/v1/public/yql?format=json&q=select%20*%20from%20" +
                "yahoo.finance.xchange%20where%20pair%20in%20(\"USDEUR\",%20\"USDUAH\")&env=store://datatables.org/alltableswithkeys";
        final String result = performRequest(request);
        final Gson gson = new GsonBuilder().create();
        final JSON json = gson.fromJson(result, JSON.class);
        for (Rate rate : json.query.results.rate) {
            System.out.println(rate.id + "=" + rate.Rate);
        }
        System.out.println("JSON: \n\t" + gson.toJson(json));
    }

    private static String performRequest(String urlStr) throws IOException {
        final URL url = new URL(urlStr);
        final StringBuilder sb = new StringBuilder();
        final HttpURLConnection http = (HttpURLConnection) url.openConnection();
        try (Reader is = new InputStreamReader(http.getInputStream());
             Reader br = new BufferedReader(is)) {
            final char[] buf = new char[1000000];
            int temp;
            do {
                if ((temp = br.read(buf)) > 0) {
                    sb.append(new String(buf, 0, temp));
                }
            } while (temp > 0);
        } finally {
            http.disconnect();
        }
        return sb.toString();
    }
}
