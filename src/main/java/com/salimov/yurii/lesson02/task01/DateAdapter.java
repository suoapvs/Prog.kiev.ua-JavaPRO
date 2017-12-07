package com.salimov.yurii.lesson02.task01;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public final class DateAdapter extends XmlAdapter<String, LocalDate> {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    @Override
    public String marshal(final LocalDate date) throws Exception {
        return date.format(DATE_FORMAT);
    }

    @Override
    public LocalDate unmarshal(final String dateStr) throws Exception {
        return LocalDate.parse(dateStr, DATE_FORMAT);
    }
}
