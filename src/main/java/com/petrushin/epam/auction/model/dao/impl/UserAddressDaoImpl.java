package com.petrushin.epam.auction.model.dao.impl;

import com.petrushin.epam.auction.exceptions.CreatorException;
import com.petrushin.epam.auction.exceptions.EntityDAOException;
import com.petrushin.epam.auction.model.creator.Creator;
import com.petrushin.epam.auction.model.dao.AbstractDao;
import com.petrushin.epam.auction.model.domain.User;
import com.petrushin.epam.auction.model.domain.UserAddress;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Dao class witch extends {@link AbstractDao} and implements methods for
 * {@link UserAddress} entity
 *
 * @author Andrei Petrushin
 * @version 1.0.0
 */
public class UserAddressDaoImpl extends AbstractDao<UserAddress> {


    public UserAddressDaoImpl(Creator<UserAddress> creator) {
        super(creator);
    }

    public UserAddress findById(Long id) throws EntityDAOException {
        return getByPK(id, UserAddress.GET_BY_ID);
    }

    public List<UserAddress> getAll() throws EntityDAOException {
        return getAll(UserAddress.GET_ALL);
    }

    public boolean save(UserAddress address) throws EntityDAOException {
        return save(address, UserAddress.ADD_ADDRESS);
    }

    public boolean delete(Long id) throws EntityDAOException {
        return delete(id, UserAddress.DELETE_BY_ID);
    }

    public boolean update(UserAddress address) throws EntityDAOException {
        return update(address, UserAddress.UPDATE_ADDRESS);
    }

    public void prepareStatementForUpdate(UserAddress address, PreparedStatement statement)
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

    @Override
    protected void prepareStatementForInsert(UserAddress userAddress, PreparedStatement statement)
            throws CreatorException {
        prepareStatementForUpdate(userAddress, statement);
    }


}
