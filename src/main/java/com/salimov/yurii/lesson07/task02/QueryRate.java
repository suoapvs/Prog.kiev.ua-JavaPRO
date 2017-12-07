package com.salimov.yurii.lesson07.task02;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@XmlRootElement(name = "query")
@XmlAccessorType(XmlAccessType.FIELD)
public final class QueryRate {

    @XmlElementWrapper(name = "results")
    @XmlElement(name = "rate")
    private Collection<Rate> rateList = new ArrayList<>();

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        for (Rate rate : this.rateList) {
            sb.append(rate).append("\n");
        }
        return sb.toString();
    }

    public void setRateList(final Collection<Rate> rateList) {
        this.rateList = rateList;
    }

    public Collection<Rate> getRateList() {
        return this.rateList;
    }

    public Rate getRate(final String id) {
        Rate result = null;
        for (Rate rate : this.rateList) {
            if (rate.getXmlID().equalsIgnoreCase(id)) {
                result = rate;
            }
        }
        return result;
    }
}
