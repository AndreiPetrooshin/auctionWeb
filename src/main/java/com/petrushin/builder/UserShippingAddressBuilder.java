package com.petrushin.builder;

import com.petrushin.builder.exceptions.UserShippingAddressBuilderException;
import com.petrushin.domain.UserShippingAddress;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserShippingAddressBuilder extends AbstractBuilder<UserShippingAddress> {


    private static final String SHIP_ADDR_ID = "ship_addr_id";
    private static final String USER_ID = "user_id";
    private static final String SA_FIRST_NAME = "sa_first_name";
    private static final String SA_SECOND_NAME = "sa_second_name";
    private static final String SA_LAST_NAME = "sa_last_name";
    private static final String SA_COUNTRY = "sa_country";
    private static final String SA_CITY = "sa_city";
    private static final String SA_STREET = "sa_street";
    private static final String SA_PHONE = "sa_phone";
    private static final String SA_POSTAL_CODE = "sa_postal_code";
    private static final String SA_IS_ACTIVE = "sa_is_active";

    public void initStatement(UserShippingAddress address,
                              PreparedStatement statement)
            throws UserShippingAddressBuilderException {
        try {
            int userId = address.getUserId();
            statement.setInt(1, userId);

            String fName = address.getFirstName();
            statement.setString(2, fName);

            String sName = address.getSecondName();
            statement.setString(3, sName);

            String lName = address.getLastName();
            statement.setString(4, lName);

            String country = address.getCountry();
            statement.setString(5, country);

            String city = address.getCity();
            statement.setString(6, city);

            String street = address.getStreet();
            statement.setString(7, street);

            String phone = address.getPhone();
            statement.setString(8, phone);

            String postalCode = address.getPostalCode();
            statement.setString(9, postalCode);

            boolean isActive = address.isActive();
            statement.setBoolean(10, isActive);

            int addrId = address.getId();
            statement.setInt(11, addrId);
        } catch (SQLException e) {
            throw new UserShippingAddressBuilderException(
                    "Error with init address statement " + e.getMessage());
        }
    }

    public UserShippingAddress createEntity(ResultSet resultSet)
            throws UserShippingAddressBuilderException {
        try {
            int shipAddrId = resultSet.getInt(SHIP_ADDR_ID);
            int userId = resultSet.getInt(USER_ID);
            String fName = resultSet.getString(SA_FIRST_NAME);
            String sName = resultSet.getString(SA_SECOND_NAME);
            String lName = resultSet.getString(SA_LAST_NAME);
            String country = resultSet.getString(SA_COUNTRY);
            String city = resultSet.getString(SA_CITY);
            String street = resultSet.getString(SA_STREET);
            String phone = resultSet.getString(SA_PHONE);
            String postalCode = resultSet.getString(SA_POSTAL_CODE);
            boolean isActive = resultSet.getBoolean(SA_IS_ACTIVE);

            return new UserShippingAddress(
                    shipAddrId, userId, fName, sName, lName, country,
                    city, street, phone, postalCode, isActive);
        } catch (SQLException e) {
            throw new UserShippingAddressBuilderException(
                    "Error with create address entity" + e.getMessage());
        }
    }
}
