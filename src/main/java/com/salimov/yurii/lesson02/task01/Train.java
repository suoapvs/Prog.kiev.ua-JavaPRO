package com.salimov.yurii.lesson02.task01;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@XmlRootElement(name = "train")
@XmlAccessorType(XmlAccessType.FIELD)
public final class Train {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm");

    @XmlAttribute
    private int id;
    @XmlElement
    private String from;
    @XmlElement
    private String to;
    @XmlJavaTypeAdapter(DateAdapter.class)
    private LocalDate date;
    @XmlJavaTypeAdapter(TimeAdapter.class)
    private LocalTime departure;

    public Train() {
    }

    public Train(
            final int id, final String from, final String to,
            final String date, final String departure
    ) {
        this(id, from, to, LocalDate.parse(date, DATE_FORMAT), LocalTime.parse(departure, TIME_FORMAT));
    }

    public Train(
            final int id, final String from, final String to,
            final LocalDate date, final LocalTime departure
    ) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.date = date;
        this.departure = departure;
    }

    @Override
    public String toString() {
        return "№ " + this.id +
                ": " + this.from + " - " + this.to +
                ", date: " + this.date.format(DATE_FORMAT) +
                " " + this.departure.format(TIME_FORMAT) +
                "\r\n";
    }

    public void setId(final int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setFrom(final String from) {
        this.from = from;
    }

    public String getFrom() {
        return this.from;
    }

    public void setTo(final String to) {
        this.to = to;
    }

    public String getTo() {
        return this.to;
    }

    public void setDate(final LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public void setDeparture(final LocalTime departure) {
        this.departure = departure;
    }

    public LocalTime getDeparture() {
        return this.departure;
    }
}
