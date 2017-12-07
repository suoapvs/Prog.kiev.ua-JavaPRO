package com.salimov.yurii.lesson06.task03;

public final class Order {

    private int clientId;
    private int productId;
    private String comment;

    public Order(
            final int clientId,
            final int productId,
            final String comment
    ) {
        this.clientId = clientId;
        this.productId = productId;
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Order:\n" +
                "\tclientId - " + this.clientId + "\n" +
                "\tproductId - " + this.productId + "\n" +
                "\tcomment - " + this.comment;
    }

    public void setClientId(final int client) {
        this.clientId = client;
    }

    public int getClientId() {
        return this.clientId;
    }

    public void setProductId(final int productId) {
        this.productId = productId;
    }

    public int getProductId() {
        return this.productId;
    }

    public void setComment(final String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return this.comment;
    }
}
