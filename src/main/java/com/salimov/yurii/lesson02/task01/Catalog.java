package com.salimov.yurii.lesson02.task01;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@XmlRootElement(name = "trains")
public final class Catalog {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm");

    @XmlElement(name = "train")
    private final List<Train> trains = new ArrayList<>();

    public void add(final Train train) {
        if (train != null && !this.trains.contains(train)) {
            this.trains.add(train);
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        for (Train t : this.trains) {
            sb.append(t);
        }
        return sb.toString();
    }

    public Catalog filter(
            final LocalDate date,
            final LocalTime fromTime,
            final LocalTime toTime
    ) {
        return filter(this, date, fromTime, toTime);
    }

    public Catalog filter(
            final String date,
            final String fromTime,
            final String toTime
    ) {
        return filter(this, date, fromTime, toTime);
    }

    public static Catalog filter(
            final Catalog catalog,
            final String dateStr,
            final String fromTimeStr,
            final String toTimeStr
    ) {
        final LocalDate date = LocalDate.parse(dateStr, DATE_FORMAT);
        final LocalTime fromTime = LocalTime.parse(fromTimeStr, TIME_FORMAT);
        final LocalTime toTime = LocalTime.parse(toTimeStr, TIME_FORMAT);
        return filter(catalog, date, fromTime, toTime);
    }

    public static Catalog filter(
            final Catalog catalog,
            final LocalDate date,
            final LocalTime fromTime,
            final LocalTime toTime
    ) {
        final Catalog newCatalog = new Catalog();
        for (Train train : catalog.trains) {
            if (date.equals(train.getDate()) &&
                    (fromTime.compareTo(train.getDeparture()) <= 0) &&
                    (toTime.compareTo(train.getDeparture()) >= 0)) {
                newCatalog.add(train);
            }
        }
        return newCatalog;
    }
}
