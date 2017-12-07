package com.salimov.yurii.lesson02.task01;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public final class Parser {

    public static <T> void write(final String path, final T catalog) {
        try {
            final File file = new File(path);
            final JAXBContext jaxbContext = JAXBContext.newInstance(catalog.getClass());
            final Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(catalog, file);
        } catch (JAXBException ex) {
            ex.printStackTrace();
        }
    }

    public static <T> T read(final String path) {
        T catalog = null;
        try {
            final File file = new File(path);
            final JAXBContext jaxbContext = JAXBContext.newInstance(Catalog.class);
            final Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            catalog = (T) unmarshaller.unmarshal(file);
        } catch (JAXBException ex) {
            ex.printStackTrace();
        }
        return catalog;
    }
}

