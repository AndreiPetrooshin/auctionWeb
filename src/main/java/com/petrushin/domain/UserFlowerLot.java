package com.petrushin.domain;

public class UserFlowerLot {

    public static final String GET_ALL = "SELECT * FROM flower_lot";
    public static final String ADD_LOT = "INSERT INTO flower_lot (user_id, fl_type, " +
            "fl_name, fl_description, fl_start_price) VALUES(?,?,?,?,?)";
    public static String GET_BY_ID = "SELECT * FROM flower_lot WHERE fl_id=?";
    private int id;
    private int userId;
    private String type;
    private String name;
    private String description;
    private double startPrice;
    private String state;

    public UserFlowerLot(int id, int userId, String type,
                         String name, String description,
                         double startPrice, String state) {
        this.id = id;
        this.userId = userId;
        this.type = type;
        this.name = name;
        this.description = description;
        this.startPrice = startPrice;
        this.state = state;
    }

    public UserFlowerLot() {
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

    public double getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(double startPrice) {
        this.startPrice = startPrice;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "UserFlowerLot{" +
                "id=" + id +
                ", userId=" + userId +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", startPrice=" + startPrice +
                ", state='" + state + '\'' +
                '}';
    }
}
