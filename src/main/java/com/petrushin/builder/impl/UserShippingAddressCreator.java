package com.petrushin.builder.impl;

import com.petrushin.builder.AbstractCreator;
import com.petrushin.builder.Creator;
import com.petrushin.domain.User;
import com.petrushin.domain.UserShippingAddress;
import com.petrushin.exceptions.CreatorException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserShippingAddressCreator extends AbstractCreator<UserShippingAddress> {


    private static final String SHIP_ADDR_ID = "ship_addr_id";
    private static final String SA_FIRST_NAME = "sa_first_name";
    private static final String SA_SECOND_NAME = "sa_second_name";
    private static final String SA_LAST_NAME = "sa_last_name";
    private static final String SA_COUNTRY = "sa_country";
    private static final String SA_CITY = "sa_city";
    private static final String SA_STREET = "sa_street";
    private static final String SA_PHONE = "sa_phone";
    private static final String SA_POSTAL_CODE = "sa_postal_code";
    private static final String SA_IS_ACTIVE = "sa_is_active";

    private Creator<User> userCreator;

    public UserShippingAddressCreator(Creator<User> userCreator) {
        this.userCreator = userCreator;
    }

    public void initStatement(UserShippingAddress address,
                              PreparedStatement statement)
            throws CreatorException {
        try {
            User user = address.getUser();
            Long userId = user.getId();
            statement.setLong(1, userId);

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

            Long addrId = address.getId();
            statement.setLong(11, addrId);
        } catch (SQLException e) {
            throw new CreatorException(
                    "Error with init address statement " + e.getMessage(), e);
        }
    }

    public UserShippingAddress createEntity(ResultSet resultSet)
            throws CreatorException {
        try {
            Long shipAddrId = resultSet.getLong(SHIP_ADDR_ID);
            User user = userCreator.createEntity(resultSet);
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
                    shipAddrId, user, fName, sName, lName, country,
                    city, street, phone, postalCode, isActive);
        } catch (SQLException | CreatorException e) {
            throw new CreatorException(
                    "Error with create address entity" + e.getMessage(), e);
        }
    }
}
