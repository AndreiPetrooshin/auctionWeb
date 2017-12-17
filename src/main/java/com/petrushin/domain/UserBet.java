package com.petrushin.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class UserBet implements Serializable {

    private static final long serialVersionUID = 2L;

    public static final String GET_BY_ID = "SELECT * FROM bets_m2m WHERE fl_id=?";
    public static final String GET_ALL = "SELECT * FROM bets_m2m";
    public static final String ADD_BET = "INSERT INTO bets_m2m (user_id, user_bet, fl_id    )" +
            " VALUES(?,?,?)";
    public static final String DELETE_BY_USER_ID = "DELETE FROM bets_m2m WHERE user_id=?";
    public static final String DELETE_BY_LOT_ID = "DELETE FROM bets_m2m WHERE fl_id=?";
    public static final String DELETE_BY_USER_AND_LOT_ID =
            "DELETE FROM bets_m2m WHERE user_id=? AND fl_id=?";
    public static final String UPDATE_BET = "UPDATE bets_m2m SET user_id=?, user_bet=? WHERE fl_id=?";


    private Long id;
    private User user;
    private BigDecimal bet;

    public UserBet(Long id, User user, BigDecimal bet) {
        this.id = id;
        this.user = user;
        this.bet = bet;
    }

    public UserBet() {
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

    public BigDecimal getBet() {
        return bet;
    }

    public void setBet(BigDecimal bet) {
        this.bet = bet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UserBet userBet = (UserBet) o;

        if (id != null ? !id.equals(userBet.id) : userBet.id != null) {
            return false;
        }
        if (user != null ? !user.equals(userBet.user) : userBet.user != null) {
            return false;
        }
        return bet != null ? bet.equals(userBet.bet) : userBet.bet == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (bet != null ? bet.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserBet{" +
                "id=" + id +
                ", user=" + user +
                ", bet=" + bet +
                '}';
    }
}
