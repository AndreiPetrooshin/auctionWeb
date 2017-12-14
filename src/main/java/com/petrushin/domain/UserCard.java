package com.petrushin.domain;

public class UserCard {

    public static final String GET_BY_ID = "SELECT * FROM user_cards WHERE card_id=?";
    public static final String GET_ALL = "SELECT * FROM user_cards";
    public static final String ADD_CARD = "INSERT INTO user_cards (user_id, " +
            "card_number, card_name, card_id) VALUES (?,?,?)";
    public static final String UPDATE_USER_CARD = "UPDATE user_cards SET user_id=?," +
            " card_number=?, card_name=? WHERE card_id=?";
    public static final String DELETE_USER_CARD = "DELETE FROM user_cards WHERE card_id=?";
    private int id;
    private int userId;
    private String cardNumber;
    private String cardName;

    public UserCard(int id, int userId, String cardNumber, String cardName) {
        this.id = id;
        this.userId = userId;
        this.cardNumber = cardNumber;
        this.cardName = cardName;
    }

    public UserCard() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UserCard userCard = (UserCard) o;

        if (id != userCard.id) {
            return false;
        }
        if (userId != userCard.userId) {
            return false;
        }
        if (cardNumber != null ?
                !cardNumber.equals(userCard.cardNumber) : userCard.cardNumber != null) {
            return false;
        }
        return cardName != null ? cardName.equals(userCard.cardName) : userCard.cardName == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + userId;
        result = 31 * result + (cardNumber != null ? cardNumber.hashCode() : 0);
        result = 31 * result + (cardName != null ? cardName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserCard{" +
                "id=" + id +
                ", userId=" + userId +
                ", cardNumber='" + cardNumber + '\'' +
                ", cardName='" + cardName + '\'' +
                '}';
    }
}
