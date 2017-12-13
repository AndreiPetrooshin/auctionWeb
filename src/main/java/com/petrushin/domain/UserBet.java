package com.petrushin.domain;

public class UserBet {

    public static final String GET_BY_ID = "SELECT * FROM bets_m2m WHERE fl_id=?";
    public static final String GET_ALL = "SELECT * FROM bets_m2m";
    public static final String ADD_BET = "INSERT INTO bets_m2m (fl_id, user_id, user_bet)" +
            " VALUES(?,?,?)";
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
    public String toString() {
        return "UserBet{" +
                "lotId=" + lotId +
                ", userId=" + userId +
                ", bet=" + bet +
                '}';
    }
}
