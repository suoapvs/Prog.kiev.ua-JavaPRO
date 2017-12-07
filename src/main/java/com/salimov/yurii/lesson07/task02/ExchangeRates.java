package com.salimov.yurii.lesson07.task02;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.List;

public final class ExchangeRates extends Thread {

    private static String link = "http://query.yahooapis.com/v1/public/yql?" +
            "format=xml&q=select*%20from%20yahoo.finance.xchange%20where%20pair%20in%20" +
            "(\"USDEUR\",\"USDUAH\",\"EURUAH\")&env=store://datatables.org/alltableswithkeys";

    private static ExchangeRates exchangeRates;
    private static RateDB rateDB;

    private static QueryRate query;
    private static boolean started;
    private static int timeout = 300000;

    private ExchangeRates() {
    }

    public static ExchangeRates getInstance() {
        if (exchangeRates == null) {
            exchangeRates = new ExchangeRates();
            rateDB = RateDB.getInstance();
            rateDB.connect();
        }
        return exchangeRates;
    }

    @Override
    public void start() {
        if (!started) {
            started = true;
            super.setDaemon(true);
            super.start();
        }
    }

    @Override
    public void run() {
        try {
            downloadRates();
        } catch (MalformedURLException | JAXBException ex) {
            ex.printStackTrace();
        }
    }


    private void downloadRates() throws MalformedURLException, JAXBException {
        final URL url = new URL(link);
        final JAXBContext jaxbContext = JAXBContext.newInstance(QueryRate.class);
        final Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        while (!isInterrupted()) {
            try {
                query = (QueryRate) unmarshaller.unmarshal(url);
                dataBaseUpdate();
                Thread.sleep(timeout);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
                break;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        rateDB.disconnect();
    }

    private static void dataBaseUpdate() {
        final Collection<Rate> list = query.getRateList();
        for (Rate rate : list) {
            rateDB.add(rate);
        }
    }

    public static Rate getRate(final String id) {
        return query.getRate(id);
    }

    public Collection<Rate> getRateList() {
        return query.getRateList();
    }

    public static void setLink(final String link) {
        ExchangeRates.link = link;
    }

    public static String getLink() {
        return link;
    }

    public static void setTimeout(final int millisecond) {
        if (millisecond > 0) {
            ExchangeRates.timeout = millisecond;
        }
    }

    public static int getTimeout() {
        return timeout;
    }

    public static boolean isStarted() {
        return started;
    }
}