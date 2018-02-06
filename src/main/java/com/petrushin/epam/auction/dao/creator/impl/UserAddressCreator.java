package com.petrushin.epam.auction.dao.creator.impl;

import com.petrushin.epam.auction.dao.creator.AbstractCreator;
import com.petrushin.epam.auction.dao.creator.Creator;
import com.petrushin.epam.auction.domain.User;
import com.petrushin.epam.auction.domain.UserAddress;
import com.petrushin.epam.auction.exceptions.CreatorException;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Extends the {@link AbstractCreator} class
 * and creates the UserAddress entity
 *
 * @author Andrei Petrushin
 * @version 1.0.0
 */
public class UserAddressCreator extends AbstractCreator<UserAddress> {


    private static final String PARAM_SHIP_ADDR_ID = "ship_addr_id";
    private static final String PARAM_SA_FIRST_NAME = "sa_first_name";
    private static final String PARAM_SA_SECOND_NAME = "sa_second_name";
    private static final String PARAM_SA_LAST_NAME = "sa_last_name";
    private static final String PARAM_SA_COUNTRY = "sa_country";
    private static final String PARAM_SA_CITY = "sa_city";
    private static final String PARAM_SA_STREET = "sa_street";
    private static final String PARAM_SA_PHONE = "sa_phone";
    private static final String PARAM_SA_POSTAL_CODE = "sa_postal_code";

    private Creator<User> userCreator;

    public UserAddressCreator(Creator<User> userCreator) {
        this.userCreator = userCreator;
    }

    @Override
    public UserAddress createEntity(ResultSet resultSet) throws CreatorException {
        try {
            Long shipAddrId = resultSet.getLong(PARAM_SHIP_ADDR_ID);
            User user = userCreator.createEntity(resultSet);
            String fName = resultSet.getString(PARAM_SA_FIRST_NAME);
            String sName = resultSet.getString(PARAM_SA_SECOND_NAME);
            String lName = resultSet.getString(PARAM_SA_LAST_NAME);
            String country = resultSet.getString(PARAM_SA_COUNTRY);
            String city = resultSet.getString(PARAM_SA_CITY);
            String street = resultSet.getString(PARAM_SA_STREET);
            String phone = resultSet.getString(PARAM_SA_PHONE);
            String postalCode = resultSet.getString(PARAM_SA_POSTAL_CODE);
            return new UserAddress(
                    shipAddrId, user, fName, sName, lName, country,
                    city, street, phone, postalCode);
        } catch (SQLException | CreatorException e) {
            throw new CreatorException(
                    "Error with create address entity" + e.getMessage(), e);
        }
    }
}
