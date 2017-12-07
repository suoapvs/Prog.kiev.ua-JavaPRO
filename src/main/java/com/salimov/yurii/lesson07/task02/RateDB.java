package com.salimov.yurii.lesson07.task02;

import javax.persistence.*;
import javax.persistence.criteria.*;
import java.util.List;

public final class RateDB {

    private static RateDB dataBase;

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    private CriteriaBuilder criteriaBuilder;
    private CriteriaQuery<Rate> criteriaQuery;
    private Root<Rate> root;

    private RateDB() {
    }

    public static RateDB getInstance() {
        if (dataBase == null) {
            dataBase = new RateDB();
        }
        return dataBase;
    }

    public void connect(final String persistenceUnit) {
        this.entityManagerFactory = Persistence.createEntityManagerFactory(persistenceUnit);
        this.entityManager = this.entityManagerFactory.createEntityManager();
        this.criteriaBuilder = this.entityManager.getCriteriaBuilder();
        this.criteriaQuery = this.criteriaBuilder.createQuery(Rate.class);
        this.root = this.criteriaQuery.from(Rate.class);
    }

    public void disconnect() {
        this.entityManager.close();
        this.entityManagerFactory.close();
    }

    public void add(final Rate rate) {
        beginTransaction();
        try {
            if (selection(rate.getXmlID()) != null) {
                change(rate);
            } else {
                System.out.println("persist");
                this.entityManager.persist(rate);
            }
            commitTransaction();
        } catch (Exception ex) {
            ex.printStackTrace();
            rollbackTransaction();
        }
    }

    public void change(final Rate newRate) {
        final Rate rate = selection(newRate.getXmlID());
        rate.setRate(newRate.getRate());
        rate.setAsk(newRate.getAsk());
        rate.setBid(newRate.getBid());
        rate.setDate(newRate.getDate());
        rate.setTime(newRate.getTime());
    }

    public Rate get(final long id) {
        final Rate rate = this.entityManager.find(Rate.class, id);
        if (rate == null) {
            System.err.println("Rate not found!");
        }
        return rate;
    }

    public Rate selection(final String xmlID) {
        Rate rate = null;
        try {
            final Path<String> path = this.root.get("xmlID");
            final Predicate predicate = this.criteriaBuilder.equal(path, xmlID);
            this.criteriaQuery.where(predicate);
            final Query query = this.entityManager.createQuery(this.criteriaQuery);
            rate = (Rate) query.getSingleResult();
        } catch (NoResultException | NonUniqueResultException ex) {
            ex.printStackTrace();
        }
        return rate;
    }

    private void beginTransaction() {
        final EntityTransaction transaction = this.entityManager.getTransaction();
        transaction.begin();
    }

    private void commitTransaction() {
        final EntityTransaction transaction = this.entityManager.getTransaction();
        transaction.commit();
    }

    private void rollbackTransaction() {
        final EntityTransaction transaction = this.entityManager.getTransaction();
        transaction.rollback();
    }
}
