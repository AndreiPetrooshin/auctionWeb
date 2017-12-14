package com.petrushin.domain;

public class UserFlowerLot {

    public static final String GET_ALL = "SELECT * FROM flower_lot";
    public static final String ADD_LOT = "INSERT INTO flower_lot (user_id, fl_type, " +
            "fl_name, fl_description, fl_start_price, fl_id) VALUES(?,?,?,?,?,?)";
    public static final String DELETE_BY_ID = "DELETE FROM flower_lot WHERE fl_id=?";
    public static final String UPDATE_FLOWER_LOT = "UPDATE flower_lot SET " +
            "user_id=?";
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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UserFlowerLot flowerLot = (UserFlowerLot) o;

        if (id != flowerLot.id) {
            return false;
        }
        if (userId != flowerLot.userId) {
            return false;
        }
        if (Double.compare(flowerLot.startPrice, startPrice) != 0) {
            return false;
        }
        if (type != null ?
                !type.equals(flowerLot.type) : flowerLot.type != null) {
            return false;
        }
        if (name != null ?
                !name.equals(flowerLot.name) : flowerLot.name != null) {
            return false;
        }
        if (description != null ?
                !description.equals(flowerLot.description) : flowerLot.description != null) {
            return false;
        }
        return state != null ? state.equals(flowerLot.state) : flowerLot.state == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + userId;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        temp = Double.doubleToLongBits(startPrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (state != null ? state.hashCode() : 0);
        return result;
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
