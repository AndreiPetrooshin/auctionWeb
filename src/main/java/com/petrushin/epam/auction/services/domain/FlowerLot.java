package com.petrushin.epam.auction.services.domain;

import java.math.BigDecimal;

/**
 * This class is a representation of the flower_lot table from a database
 *
 * @author Andrei Petrushin
 * @version 1.0.0
 */
public class FlowerLot implements Identified {

    /**
     * SQL queries for data base
     */
    public static final String GET_ALL =
            "SELECT l.fl_id, l.fl_name, l.fl_description, l.fl_start_price, " +
                    "l.fl_state, l.fl_type, u.user_id, u.u_login, u.u_password," +
                    " u.u_email, r.role_id, r.user_role FROM flower_lot l " +
                    "JOIN user u, user_role r WHERE l.user_id=u.user_id  " +
                    "AND u.role_id=r.role_id;";

    public static final String ADD_LOT = "INSERT INTO flower_lot (user_id, fl_type, " +
            "fl_name, fl_description, fl_start_price, fl_id) VALUES(?,?,?,?,?,?)";

    public static final String DELETE_BY_ID = "DELETE FROM flower_lot WHERE fl_id=?";

    public static final String UPDATE_FLOWER_LOT = "UPDATE flower_lot SET user_id=?, fl_type=?, " +
            "fl_name=?, fl_description=?, fl_start_price=?, fl_state=? WHERE fl_id=?";

    public static final String GET_BY_ID =
            "SELECT l.fl_id, l.fl_name, l.fl_description, l.fl_start_price," +
                    " l.fl_state, l.fl_type, u.user_id, u.u_login, u.u_password," +
                    " u.u_email, r.role_id, r.user_role FROM flower_lot l " +
                    "JOIN user u, user_role r WHERE l.user_id=u.user_id " +
                    "AND l.fl_id=? AND u.role_id=r.role_id;";

    public static final String GET_BY_STATE =
            "SELECT l.fl_id, l.fl_name, l.fl_description, l.fl_start_price, " +
                    "l.fl_state, l.fl_type, u.user_id, u.u_login, u.u_password," +
                    " u.u_email, r.role_id, r.user_role FROM flower_lot l " +
                    "JOIN user u, user_role r WHERE l.user_id=u.user_id  " +
                    "AND u.role_id=r.role_id AND l.fl_state=?;";

    public static final String GET_BY_TYPE_AND_STATE =
            "SELECT l.fl_id, l.fl_name, l.fl_description, l.fl_start_price, " +
                    "l.fl_state, l.fl_type, u.user_id, u.u_login, u.u_password," +
                    " u.u_email, r.role_id, r.user_role FROM flower_lot l " +
                    "JOIN user u, user_role r WHERE l.user_id=u.user_id  " +
                    "AND u.role_id=r.role_id AND l.fl_type=? AND l.fl_state=?;";

    public static final String GET_BY_USER_ID =
            "SELECT l.fl_id, l.fl_name, l.fl_description, l.fl_start_price, " +
                    "l.fl_state, l.fl_type, u.user_id, u.u_login, u.u_password," +
                    " u.u_email, r.role_id, r.user_role FROM flower_lot l " +
                    "JOIN user u, user_role r WHERE l.user_id=u.user_id  " +
                    "AND u.role_id=r.role_id AND l.user_id=?";


    private static final long serialVersionUID = 4L;

    private Long id;
    private User user;
    private String type;
    private String name;
    private String description;
    private BigDecimal startPrice;
    private String state;

    public FlowerLot(Long id, User user, String type,
                     String name, String description,
                     BigDecimal startPrice, String state) {
        this.id = id;
        this.user = user;
        this.type = type;
        this.name = name;
        this.description = description;
        this.startPrice = startPrice;
        this.state = state;
    }

    public FlowerLot() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(BigDecimal startPrice) {
        this.startPrice = startPrice;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FlowerLot flowerLot = (FlowerLot) o;

        if (id != null ? !id.equals(flowerLot.id) : flowerLot.id != null) {
            return false;
        }
        if (user != null ? !user.equals(flowerLot.user) : flowerLot.user != null) {
            return false;
        }
        if (type != null ? !type.equals(flowerLot.type) : flowerLot.type != null) {
            return false;
        }
        if (name != null ? !name.equals(flowerLot.name) : flowerLot.name != null) {
            return false;
        }
        if (description != null ? !description.equals(flowerLot.description) : flowerLot.description != null) {
            return false;
        }
        if (startPrice != null ? !startPrice.equals(flowerLot.startPrice) : flowerLot.startPrice != null) {
            return false;
        }
        return state != null ? state.equals(flowerLot.state) : flowerLot.state == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (startPrice != null ? startPrice.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        return result;
    }


    @Override
    public String toString() {
        return "FlowerLot{" +
                "id=" + id +
                ", user=" + user +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", startPrice=" + startPrice +
                ", state='" + state + '\'' +
                '}';
    }
}
