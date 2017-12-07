package com.salimov.yurii.lesson07.task02;

import javax.persistence.*;
import javax.xml.bind.annotation.*;

@Entity
@Table(name = "Rates")
@XmlRootElement(name = "rate")
@XmlAccessorType(XmlAccessType.FIELD)
public final class Rate implements Cloneable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    @XmlAttribute(name = "id")
    private String xmlID;

    @XmlElement(name = "Name")
    private String name;

    @XmlElement(name = "Rate")
    private Double rate;

    @XmlElement(name = "Date")
    private String date;

    @XmlElement(name = "Time")
    private String time;

    @XmlElement(name = "Ask")
    private Double ask;

    @XmlElement(name = "Bid")
    private Double bid;

    @Override
    public Rate clone() {
        Rate rate = null;
        try {
            rate = (Rate) super.clone();
        } catch (CloneNotSupportedException ex) {
            ex.printStackTrace();
        }
        return rate;
    }

    @Override
    public String toString() {
        return "Rate:[" + "\n\tId:\t" + this.xmlID +
                "\n\tName:\t" + this.name +
                "\n\tRate:\t" + this.rate +
                "\n\tDate:\t" + this.date +
                "\n\tTime:\t" + this.time +
                "\n\tAsk:\t" + this.ask +
                "\n\tBid:\t" + this.bid +
                "\n]\n";
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (this.getClass() != object.getClass()) {
            return false;
        }
        Rate other = (Rate) object;
        if (!this.xmlID.equals(other.xmlID) && !this.name.equals(other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return (this.xmlID.hashCode() + this.name.hashCode());
    }

    public void setId(final long id) {
        this.id = id;
    }

    public long getId() {
        return this.id;
    }

    public void setXmlID(final String xmlID) {
        this.xmlID = xmlID;
    }

    public String getXmlID() {
        return this.xmlID;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setRate(final Double rate) {
        this.rate = rate;
    }

    public Double getRate() {
        return this.rate;
    }

    public void setDate(final String date) {
        this.date = date;
    }

    public String getDate() {
        return this.date;
    }

    public void setTime(final String time) {
        this.time = time;
    }

    public String getTime() {
        return this.time;
    }

    public void setAsk(final Double ask) {
        this.ask = ask;
    }

    public Double getAsk() {
        return this.ask;
    }

    public void setBid(final Double bid) {
        this.bid = bid;
    }

    public Double getBid() {
        return this.bid;
    }
}