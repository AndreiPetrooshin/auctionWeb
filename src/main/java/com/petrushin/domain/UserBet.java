package com.petrushin.domain;

public class UserBet {

    public static final String GET_BY_ID = "SELECT * FROM bets_m2m WHERE fl_id=?";
    public static final String GET_ALL = "SELECT * FROM bets_m2m";
    public static final String ADD_BET = "INSERT INTO bets_m2m (user_id, user_bet, fl_id    )" +
            " VALUES(?,?,?)";
    public static final String DELETE_BY_USER_ID = "DELETE FROM bets_m2m WHERE user_id=?";
    public static final String DELETE_BY_LOT_ID = "DELETE FROM bets_m2m WHERE fl_id=?";
    public static final String DELETE_BY_USER_AND_LOT_ID =
            "DELETE FROM bets_m2m WHERE user_id=? AND fl_id=?";


    private int lotId;
    private int userId;
    private double bet;

    public UserBet(int lotId, int userId, double bet) {
        this.lotId = lotId;
        this.userId = userId;
        this.bet = bet;
    }

    public UserBet() {
    }

    public int getLotId() {
        return lotId;
    }

    public void setLotId(int lotId) {
        this.lotId = lotId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getBet() {
        return bet;
    }

    public void setBet(double bet) {
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

        if (lotId != userBet.lotId) {
            return false;
        }
        if (userId != userBet.userId) {
            return false;
        }
        return Double.compare(userBet.bet, bet) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = lotId;
        result = 31 * result + userId;
        temp = Double.doubleToLongBits(bet);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "UserBet{" +
                "lotId=" + lotId +
                ", userId=" + userId +
                ", bet=" + bet +
                '}';
    }
}
