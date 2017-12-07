package com.salimov.yurii.lesson07.task01;

import javax.persistence.*;
import javax.persistence.criteria.*;
import java.util.Collection;
import java.util.List;

public final class DataBase {
    private static DataBase dataBase;

    private CriteriaBuilder criteriaBuilder;
    private CriteriaQuery<Dish> criteriaQuery;
    private Root<Dish> root;

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
        entityManager = this.entityManagerFactory.createEntityManager();
        this.criteriaBuilder = this.entityManager.getCriteriaBuilder();
        this.criteriaQuery = this.criteriaBuilder.createQuery(Dish.class);
        this.root = this.criteriaQuery.from(Dish.class);
    }

    public void disconnect() {
        this.entityManager.close();
        this.entityManagerFactory.close();
    }

    public void addDish(final Dish dish) {
        beginTransaction();
        try {
            this.entityManager.persist(dish);
            commitTransaction();
        } catch (Exception ex) {
            rollbackTransaction();
        }
    }

    public void deleteDish(final long id) {
        final Dish dish = getDish(id);
        if (dish != null) {
            beginTransaction();
            try {
                this.entityManager.remove(dish);
                commitTransaction();
            } catch (Exception ex) {
                rollbackTransaction();
            }
        }
    }

    public void changeDish(final Dish newDish) {
        final Dish dish = getDish(newDish.getId());
        if (dish != null) {
            beginTransaction();
            try {
                this.entityManager.merge(newDish);
                commitTransaction();
            } catch (Exception ex) {
                rollbackTransaction();
            }
        }
    }

    public Dish getDish(final long id) {
        final Dish dish = this.entityManager.find(Dish.class, id);
        if (dish == null) {
            System.err.println("Dish not found!");
        }
        return dish;
    }

    public Collection<Dish> getDishes() {
        this.criteriaQuery.where();
        final Query query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    public Collection<Dish> selectionByPrice(
            final double priceMin, final double priceMax
    ) {
        return selection("price", priceMin, priceMax);
    }

    public Collection<Dish> selectionByWeight(
            final double weightMin, final double weightMax
    ) {
        return selection("weight", weightMin, weightMax);
    }

    public Collection<Dish> selectionByDiscount(
            final double discountMin, final double discountMax
    ) {
        return selection("discount", discountMin, discountMax);
    }

    public Collection<Dish> selectionByDiscount() {
        return selectionByDiscount(0.001, 100);
    }

    private Collection<Dish> selection(
            final String name, final double valueMin, final double valueMax
    ) {
        final Path<Double> path = this.root.get(name);
        final Predicate predicate = this.criteriaBuilder.between(path, valueMin, valueMax);
        this.criteriaQuery.where(predicate);
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
}
