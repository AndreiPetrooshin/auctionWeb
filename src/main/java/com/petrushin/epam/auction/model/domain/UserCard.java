package com.petrushin.epam.auction.model.domain;


public class UserCard implements Identified {

    public static final String GET_BY_ID =
            "SELECT c.card_id, c.card_name, c.card_number, u.user_id, u.u_login, " +
                    "u.u_email, u.u_password, u.role_id, r.user_role " +
                    "FROM user_cards c JOIN user u, user_role r WHERE " +
                    "c.user_id=u.user_id AND u.role_id=r.role_id";
    public static final String GET_ALL =
            "SELECT c.card_id, c.card_name, c.card_number, u.user_id, u.u_login, " +
                    "u.u_email, u.u_password, u.role_id, r.user_role " +
                    "FROM user_cards c JOIN user u, user_role r " +
                    "WHERE c.user_id=u.user_id AND u.role_id=r.role_id";
    public static final String ADD_CARD = "INSERT INTO user_cards (user_id, " +
            "card_number, card_name, card_id) VALUES (?,?,?,?)";
    public static final String UPDATE_USER_CARD = "UPDATE user_cards SET user_id=?," +
            " card_number=?, card_name=? WHERE card_id=?";
    public static final String DELETE_USER_CARD = "DELETE FROM user_cards WHERE card_id=?";

    private static final long serialVersionUID = 3L;
    private Long id;
    private User user;
    private String cardNumber;
    private String cardName;

    public UserCard(Long id, User user, String cardNumber, String cardName) {
        this.id = id;
        this.user = user;
        this.cardNumber = cardNumber;
        this.cardName = cardName;
    }

    public UserCard() {
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

        if (id != null ? !id.equals(userCard.id) : userCard.id != null) {
            return false;
        }
        if (user != null ? !user.equals(userCard.user) : userCard.user != null) {
            return false;
        }
        if (cardNumber != null ? !cardNumber.equals(userCard.cardNumber) : userCard.cardNumber != null) {
            return false;
        }
        return cardName != null ? cardName.equals(userCard.cardName) : userCard.cardName == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (cardNumber != null ? cardNumber.hashCode() : 0);
        result = 31 * result + (cardName != null ? cardName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserCard{" +
                "id=" + id +
                ", user=" + user +
                ", cardNumber='" + cardNumber + '\'' +
                ", cardName='" + cardName + '\'' +
                '}';
    }
}
