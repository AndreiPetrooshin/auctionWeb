package com.petrushin.model.dao.impl;

import com.petrushin.model.creator.Creator;
import com.petrushin.model.dao.AbstractDao;
import com.petrushin.model.domain.User;
import com.petrushin.model.domain.UserAddresses;
import com.petrushin.exceptions.CreatorException;
import com.petrushin.exceptions.EntityDAOException;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class UserAddressesDaoImpl extends AbstractDao<UserAddresses> {


    public UserAddressesDaoImpl(Creator<UserAddresses> creator) {
        super(creator);
    }

    public UserAddresses findById(Long id) throws EntityDAOException {
        return getByPK(id, UserAddresses.GET_BY_ID);
    }

    public List<UserAddresses> getAll() throws EntityDAOException {
        return getAll(UserAddresses.GET_ALL);
    }

    public boolean save(UserAddresses address) throws EntityDAOException {
        return save(address, UserAddresses.ADD_ADDRESS);
    }

    public boolean delete(Long id) throws EntityDAOException {
        return delete(id, UserAddresses.DELETE_BY_ID);
    }

    public boolean update(UserAddresses address) throws EntityDAOException {
        return update(address, UserAddresses.UPDATE_ADDRESS);
    }

    public void prepareStatement(UserAddresses address, PreparedStatement statement)
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


}
