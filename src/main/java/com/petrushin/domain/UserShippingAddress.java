package com.petrushin.domain;

public class UserShippingAddress {

    public static final String GET_BY_ID = "SELECT * FROM user_shipping_address WHERE ship_addr_id=?";
    public static final String ADD_ADDRESS = "INSERT INTO user_shipping_address (user_id, " +
            "sa_first_name, sa_second_name, sa_last_name, sa_country, " +
            "sa_city, sa_street, sa_phone, sa_postal_code, sa_is_active) " +
            "VALUES(?,?,?,?,?,?,?,?,?,?)";
    public static final String GET_ALL = "SELECT * FROM user_shipping_address";

    private int id;
    private int userId;
    private String firstName;
    private String secondName;
    private String lastName;
    private String country;
    private String city;
    private String street;
    private String phone;
    private String postalCode;
    private boolean isActive;

    public UserShippingAddress(int id, int userId, String firstName,
                               String secondName, String lastName, String country,
                               String city, String street, String phone,
                               String postalCode, boolean isActive) {
        this.id = id;
        this.userId = userId;
        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
        this.country = country;
        this.city = city;
        this.street = street;
        this.phone = phone;
        this.postalCode = postalCode;
        this.isActive = isActive;
    }

    public UserShippingAddress() {
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "UserShippingAddress{" +
                "id=" + id +
                ", userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", phone='" + phone + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
