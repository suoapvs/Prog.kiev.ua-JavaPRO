package com.salimov.yurii.lesson06.task02H;

import org.hibernate.Transaction;

import javax.persistence.*;
import javax.persistence.criteria.*;
import java.util.Collection;
import java.util.List;

public final class DataBase {
    private static DataBase dataBase;

    private CriteriaBuilder criteriaBuilder;
    private CriteriaQuery<Apartment> criteriaQuery;
    private Root<Apartment> root;

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    private DataBase() {
    }

    public static DataBase getInstance() {
        if (dataBase == null) {
            dataBase = new DataBase();
        }
        return dataBase;
    }

    public void connect(final String persistenceUnit) {
        this.entityManagerFactory = Persistence.createEntityManagerFactory(persistenceUnit);
        this.entityManager = this.entityManagerFactory.createEntityManager();
        this.criteriaBuilder = this.entityManager.getCriteriaBuilder();
        this.criteriaQuery = this.criteriaBuilder.createQuery(Apartment.class);
        this.root = this.criteriaQuery.from(Apartment.class);
    }

    public void disconnect() {
        this.entityManager.close();
        this.entityManagerFactory.close();
    }

    public void addApartment(final Apartment apartment) {
        beginTransaction();
        try {
            this.entityManager.persist(apartment);
            commitTransaction();
        } catch (Exception ex) {
            ex.printStackTrace();
            rollbackTransaction();
        }
    }

    public void deleteApartment(final long id) {
        final Apartment apartment = getApartmentById(id);
        if (apartment != null) {
            beginTransaction();
            try {
                this.entityManager.remove(apartment);
                commitTransaction();
            } catch (Exception ex) {
                ex.printStackTrace();
                rollbackTransaction();
            }
        }
    }

    public Apartment getApartmentById(final long id) {
        Apartment apartment = null;
        try {
            final Path<Long> path = this.root.get("id");
            final Predicate predicate = this.criteriaBuilder.equal(path, id);
            this.criteriaQuery.where(predicate);
            final Query query = this.entityManager.createQuery(this.criteriaQuery);
            query.setParameter("id", id);
            apartment = (Apartment) query.getSingleResult();
        } catch (NoResultException ex) {
            System.err.println("Apartment not found!");
        } catch (NonUniqueResultException ex) {
            System.err.println("Non unique result!");
        }
        return apartment;
    }

    public void changeApartment(final long id, final Apartment newApartment) {
        final Apartment apartment = getApartmentById(id);
        if (apartment != null) {
            beginTransaction();
            try {
                updateApartment(newApartment, apartment);
                commitTransaction();
            } catch (Exception ex) {
                ex.printStackTrace();
                rollbackTransaction();
            }
        }
    }

    public Collection<Apartment> getApartments() {
        this.criteriaQuery.where();
        return getResultList();
    }

    public Collection<Apartment> selectionByRegion(final String region) {
        final Path<String> path = this.root.get("region");
        final Predicate predicate = this.criteriaBuilder.equal(path, region);
        return selectionByPredicate(predicate);
    }

    public Collection<Apartment> selectionByPrice(final double priceMin, final double priceMax) {
        return selection("price", priceMin, priceMax);
    }

    public Collection<Apartment> selectionByArea(final double areaMin, final double areaMax) {
        return selection("area", areaMin, areaMax);
    }

    public Collection<Apartment> selectionByRoom(final int numberMin, final int numberMax) {
        return selection("rooms", numberMin, numberMax);
    }

    private Collection<Apartment> selection(final String name, final double valueMin, final double valueMax) {
        final Path<Double> path = this.root.get(name);
        final Predicate predicate = this.criteriaBuilder.between(path, valueMin, valueMax);
        return selectionByPredicate(predicate);
    }

    private Collection<Apartment> selectionByPredicate(final Predicate predicate) {
        this.criteriaQuery.where(predicate);
        return getResultList();
    }

    private Collection<Apartment> getResultList() {
        final Query query = this.entityManager.createQuery(this.criteriaQuery);
        return query.getResultList();
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

    private void updateApartment(final Apartment from, final Apartment to) {
        to.setRegion(from.getRegion());
        to.setAddress(from.getRegion());
        to.setArea(from.getArea());
        to.setRooms(from.getRooms());
        to.setPrice(from.getPrice());
    }
}
