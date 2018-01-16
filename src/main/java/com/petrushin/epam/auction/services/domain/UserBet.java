package com.petrushin.epam.auction.services.domain;

import java.math.BigDecimal;

/**
 * This class is a representation of the bets_m2m table from a database
 *
 * @author Andrei Petrushin
 * @version 1.0.0
 */
public class UserBet implements Identified {

    private static final long serialVersionUID = 2L;

    public static final String GET_BY_ID =
            "SELECT b.bet_id, b.user_bet, l.fl_id, l.fl_description, l.fl_name," +
                    " l.fl_start_price, l.fl_state, l.fl_type, u.user_id," +
                    " u.u_login, u.u_password, u.u_email, r.role_id, " +
                    "r.user_role FROM bets_m2m b JOIN user u, flower_lot l," +
                    " user_role r WHERE b.fl_id=l.fl_id AND b.user_id=u.user_id " +
                    "AND u.role_id=r.role_id AND b.bet_id=?";

    public static final String GET_ALL =
            "SELECT b.bet_id, b.user_bet, l.fl_id, l.fl_description, l.fl_name, " +
                    "l.fl_start_price, l.fl_state, l.fl_type, u.user_id," +
                    " u.u_login, u.u_password, u.u_email, r.role_id, " +
                    "r.user_role FROM bets_m2m b JOIN user u, flower_lot l, " +
                    "user_role r WHERE b.fl_id=l.fl_id AND b.user_id=u.user_id" +
                    " AND u.role_id=r.role_id";

    public static final String ADD_BET =
            "INSERT INTO bets_m2m (fl_id, user_id, user_bet, bet_id) VALUES(?,?,?,?)";

    public static final String DELETE_BY_ID =
            "DELETE FROM bets_m2m WHERE bet_id=?";

    public static final String UPDATE_BET =
            "UPDATE bets_m2m SET fl_id=?, user_id=?, user_bet=? WHERE bet_id=?";

    public static final String GET_BY_LOT_ID =
            "SELECT b.bet_id, b.user_bet, l.fl_id, l.fl_description, l.fl_name, " +
                    "l.fl_start_price, l.fl_state, l.fl_type, u.user_id," +
                    " u.u_login, u.u_password, u.u_email, r.role_id, " +
                    "r.user_role FROM bets_m2m b JOIN user u, flower_lot l, " +
                    "user_role r WHERE b.fl_id=l.fl_id AND b.user_id=u.user_id" +
                    " AND u.role_id=r.role_id AND b.fl_id=? ORDER BY b.user_bet DESC";

    private Long id;
    private FlowerLot lot;
    private User user;
    private BigDecimal bet;

    public UserBet(Long id, FlowerLot lot, User user, BigDecimal bet) {
        this.id = id;
        this.lot = lot;
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
        if (lot != null ? !lot.equals(userBet.lot) : userBet.lot != null) {
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
        result = 31 * result + (lot != null ? lot.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (bet != null ? bet.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserBet{" +
                "id=" + id +
                ", lot=" + lot +
                ", user=" + user +
                ", bet=" + bet +
                '}';
    }
}
