package com.petrushin.epam.auction.model.domain;

import java.math.BigDecimal;

/**
 * This class is a representation of the payments table from a database
 *
 * @author Andrei Petrushin
 * @version 1.0.0
 */
public class Payment implements Identified {

    private static final long serialVersionUID = 7L;


    public static final String GET_BY_ID = "SELECT p.payment_id, p.fl_id, p.user_id, p.price, p.is_paid," +
            "f.fl_type, f.fl_name, f.fl_description, f.fl_start_price, f.fl_state," +
            "u.u_login, u.u_password, u.u_email, r.role_id, r.user_role FROM payments p, flower_lot f, user u, user_role r " +
            "WHERE p.fl_id=f.fl_id AND p.user_id=u.user_id AND u.role_id=r.role_id AND p.payment_id=?";
    public static final String GET_ALL = "SELECT p.payment_id, p.fl_id, p.user_id, p.price, p.is_paid," +
            "f.fl_type, f.fl_name, f.fl_description, f.fl_start_price, f.fl_state," +
            "u.u_login, u.u_password, u.u_email, r.role_id, r.user_role FROM payments p, flower_lot f, user u, user_role r " +
            "WHERE p.fl_id=f.fl_id AND p.user_id=u.user_id AND u.role_id=r.role_id";
    public static final String ADD_PAYMENT = "INSERT INTO payments (fl_id, user_id, price, is_paid, payment_id) " +
            "VALUES(?,?,?,?,?)";
    public static final String DELETE_BY_ID = "DELETE FROM payments WHERE payments_id=?";
    public static final String UPDATE_PAYMENT = "UPDATE payments SET fl_id=?, user_id=?, price=?, is_paid=? " +
            "WHERE payment_id=?";

    private Long id;
    private FlowerLot lot;
    private User user;
    private BigDecimal price;
    private boolean paid = false;


    public Payment() {
    }

    public Payment(Long id, FlowerLot lot, User user, BigDecimal price, boolean paid) {
        this.id = id;
        this.lot = lot;
        this.user = user;
        this.price = price;
        this.paid = paid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FlowerLot getLot() {
        return lot;
    }

    public void setLot(FlowerLot lot) {
        this.lot = lot;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Payment payment = (Payment) o;

        if (paid != payment.paid) {
            return false;
        }
        if (id != null ? !id.equals(payment.id) : payment.id != null) {
            return false;
        }
        if (lot != null ? !lot.equals(payment.lot) : payment.lot != null) {
            return false;
        }
        if (user != null ? !user.equals(payment.user) : payment.user != null) {
            return false;
        }
        return price != null ? price.equals(payment.price) : payment.price == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (lot != null ? lot.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (paid ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", lot=" + lot +
                ", user=" + user +
                ", price=" + price +
                ", paid=" + paid +
                '}';
    }
}
