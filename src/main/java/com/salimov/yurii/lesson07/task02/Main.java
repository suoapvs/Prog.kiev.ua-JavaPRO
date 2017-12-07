package com.salimov.yurii.lesson07.task02;

import javax.persistence.*;

/**
 * 7.2 Создать базу данных «Банк» с таблицами «Пользователи», «Транзакции», «Счета»
 * и «Курсы валют». Счет бывает 3-х видов: USD, EUR, UAH. Написать запросы для
 * пополнения счета в нужной валюте, перевода средств с одного счета на другой,
 * конвертации валюты по курсу в рамках счетов одного пользователя. Написать
 * запрос для получения суммарных средств на счету одного пользователя в UAH
 * (расчет по курсу).
 *
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 */
public class Main {

    private final static String PERSISTENCE_UNIT = "JPA_task72";

    public static void main(String[] args) throws InterruptedException {
        TablesMySql.create();

        final ExchangeRates er = ExchangeRates.getInstance();
        er.start();

        Thread.sleep(3000);

        final RateDB rateDB = RateDB.getInstance();
        final Rate rate = rateDB.selection("USDEUR");
        rateDB.connect(PERSISTENCE_UNIT);
        System.out.println(rate);

        final EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
        final EntityManager em = emf.createEntityManager();
        try {
            final Client client = new Client("Yuriy", "Salimov", "+380637399290");
            final Address address = new Address("Ukraine", "Kyiv", "Lomonosova", 55, "03032");
            client.setAddress(address);
            final Profile profile = new Profile("login", "password");
            profile.setClient(client);

            final Account a1 = new Account(10001, "UAH");
            final Account a2 = new Account(10002, "USD");
            final Account a3 = new Account(10003, "EUR");
            profile.addAccount(a1);
            profile.addAccount(a2);
            profile.addAccount(a3);

            em.getTransaction().begin();
            em.persist(profile);
            em.getTransaction().commit();
            System.out.println("DONE");
        } catch (Exception ex) {
            ex.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
            emf.close();
        }
    }
}
