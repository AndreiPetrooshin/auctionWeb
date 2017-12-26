package com.petrushin.model.creator.impl;

import com.petrushin.model.creator.AbstractCreator;
import com.petrushin.model.creator.Creator;
import com.petrushin.model.domain.User;
import com.petrushin.model.domain.UserAddresses;
import com.petrushin.exceptions.CreatorException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserAddressesCreator extends AbstractCreator<UserAddresses> {


    private static final String PARAM_SHIP_ADDR_ID = "ship_addr_id";
    private static final String PARAM_SA_FIRST_NAME = "sa_first_name";
    private static final String PARAM_SA_SECOND_NAME = "sa_second_name";
    private static final String PARAM_SA_LAST_NAME = "sa_last_name";
    private static final String PARAM_SA_COUNTRY = "sa_country";
    private static final String PARAM_SA_CITY = "sa_city";
    private static final String PARAM_SA_STREET = "sa_street";
    private static final String PARAM_SA_PHONE = "sa_phone";
    private static final String PARAM_SA_POSTAL_CODE = "sa_postal_code";
    private static final String PARAM_SA_IS_ACTIVE = "sa_is_active";

    private Creator<User> userCreator;

    public UserAddressesCreator(Creator<User> userCreator) {
        this.userCreator = userCreator;
    }

    @Override
    public UserAddresses createEntity(ResultSet resultSet) throws CreatorException {
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
            boolean isActive = resultSet.getBoolean(PARAM_SA_IS_ACTIVE);
            return new UserAddresses(
                    shipAddrId, user, fName, sName, lName, country,
                    city, street, phone, postalCode, isActive);
        } catch (SQLException | CreatorException e) {
            throw new CreatorException(
                    "Error with create address entity" + e.getMessage(), e);
        }
    }
}
