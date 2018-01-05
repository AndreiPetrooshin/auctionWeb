package com.petrushin.epam.auction.model.domain;

/**
 * This class is a representation of the user_shipping_address table from a database
 *
 * @author Andrei Petrushin
 * @version 1.0.0
 */
public class UserAddress implements Identified {

    public static final String GET_BY_ID =
            "SELECT a.ship_addr_id, a.sa_first_name, a.sa_second_name, " +
                    "a.sa_last_name, a.sa_country, a.sa_city, a.sa_street," +
                    " a.sa_phone, a.sa_postal_code, a.sa_is_active, u.user_id," +
                    " u.u_login, u.u_password, u.u_email, r.role_id, r.user_role" +
                    " FROM user_shipping_address a JOIN user u, user_role r " +
                    "WHERE a.user_id=u.user_id AND u.role_id=r.role_id" +
                    " AND a.ship_addr_id=?";
    public static final String GET_ALL =
            "SELECT a.ship_addr_id, a.sa_first_name, a.sa_second_name, " +
                    "a.sa_last_name, a.sa_country, a.sa_city, a.sa_street," +
                    " a.sa_phone, a.sa_postal_code, a.sa_is_active, u.user_id," +
                    " u.u_login, u.u_password, u.u_email, r.role_id, r.user_role" +
                    " FROM user_shipping_address a JOIN user u, user_role r" +
                    " WHERE a.user_id=u.user_id AND u.role_id=r.role_id";
    public static final String ADD_ADDRESS =
            "INSERT INTO user_shipping_address (user_id, sa_first_name," +
                    " sa_second_name, sa_last_name, sa_country, sa_city," +
                    " sa_street, sa_phone, sa_postal_code, sa_is_active, " +
                    "ship_addr_id) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
    public static final String DELETE_BY_ID =
            "DELETE FROM user_shipping_address WHERE ship_addr_id=?";
    public static final String UPDATE_ADDRESS =
            "UPDATE user_shipping_address SET user_id=?, sa_first_name=?," +
                    " sa_second_name=?, sa_last_name=?, sa_country=?, " +
                    "sa_city=?, sa_street=?, sa_phone=?, sa_postal_code=?," +
                    " sa_is_active=? WHERE ship_addr_id=?";
    private static final long serialVersionUID = 6L;
    private Long id;
    private User user;
    private String firstName;
    private String secondName;
    private String lastName;
    private String country;
    private String city;
    private String street;
    private String phone;
    private String postalCode;
    private boolean active;


    public UserAddress(Long id, User user, String firstName,
                       String secondName, String lastName,
                       String country, String city, String street,
                       String phone, String postalCode, boolean active) {
        this.id = id;
        this.user = user;
        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
        this.country = country;
        this.city = city;
        this.street = street;
        this.phone = phone;
        this.postalCode = postalCode;
        this.active = active;
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
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UserAddress address = (UserAddress) o;

        if (active != address.active) {
            return false;
        }
        if (id != null ? !id.equals(address.id)
                : address.id != null) {
            return false;
        }
        if (user != null ? !user.equals(address.user)
                : address.user != null) {
            return false;
        }
        if (firstName != null ? !firstName.equals(address.firstName)
                : address.firstName != null) {
            return false;
        }
        if (secondName != null ? !secondName.equals(address.secondName)
                : address.secondName != null) {
            return false;
        }
        if (lastName != null ? !lastName.equals(address.lastName)
                : address.lastName != null) {
            return false;
        }
        if (country != null ? !country.equals(address.country)
                : address.country != null) {
            return false;
        }
        if (city != null ? !city.equals(address.city)
                : address.city != null) {
            return false;
        }
        if (street != null ? !street.equals(address.street)
                : address.street != null) {
            return false;
        }
        if (phone != null ? !phone.equals(address.phone)
                : address.phone != null) {
            return false;
        }
        return postalCode != null ? postalCode.equals(address.postalCode)
                : address.postalCode == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (secondName != null ? secondName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (street != null ? street.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (postalCode != null ? postalCode.hashCode() : 0);
        result = 31 * result + (active ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserAddress{" +
                "id=" + id +
                ", user=" + user +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", phone='" + phone + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", active=" + active +
                '}';
    }


}
