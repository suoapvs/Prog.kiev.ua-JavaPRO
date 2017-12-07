package com.salimov.yurii.lesson02.task01;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public final class TimeAdapter extends XmlAdapter<String, LocalTime> {

    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm");

    @Override
    public String marshal(final LocalTime time) {
        return time.format(TIME_FORMAT);
    }

    @Override
    public LocalTime unmarshal(final String time) {
        return LocalTime.parse(time, TIME_FORMAT);
    }
}
