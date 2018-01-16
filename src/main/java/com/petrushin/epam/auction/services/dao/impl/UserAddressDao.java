package com.petrushin.epam.auction.services.dao.impl;

import com.petrushin.epam.auction.exceptions.CreatorException;
import com.petrushin.epam.auction.exceptions.EntityDAOException;
import com.petrushin.epam.auction.services.creator.Creator;
import com.petrushin.epam.auction.services.dao.AbstractDao;
import com.petrushin.epam.auction.services.dao.ConnectionPool;
import com.petrushin.epam.auction.services.domain.User;
import com.petrushin.epam.auction.services.domain.UserAddress;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Dao class witch extends {@link AbstractDao} and implements methods for
 * {@link UserAddress} entity
 *
 * @author Andrei Petrushin
 * @version 1.0.0
 */
public class UserAddressDao extends AbstractDao<UserAddress> {


    private static final Logger LOGGER = LogManager.getLogger(UserAddressDao.class);

    public UserAddressDao(Creator<UserAddress> creator) {
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

    public UserAddress getByUserId(Long id) throws EntityDAOException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(UserAddress.GET_BY_USER_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            UserAddress userAddress = null;
            if (resultSet.next()) {
                userAddress = creator.createEntity(resultSet);
            }
            return userAddress;
        } catch (SQLException | CreatorException e) {
            LOGGER.error("Error with getByUserId operation", e);
            throw new EntityDAOException(e.getMessage(), e);
        } finally {
            ConnectionPool.getInstance().returnConnection(connection);
        }
    }

    public void prepareStatementForUpdate(UserAddress address, PreparedStatement statement)
            throws EntityDAOException {
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

            Long addrId = address.getId();
            statement.setLong(10, addrId);
        } catch (SQLException e) {
            throw new EntityDAOException(
                    "Error with init address statement " + e.getMessage(), e);
        }
    }


    @Override
    protected void prepareStatementForInsert(UserAddress userAddress, PreparedStatement statement)
            throws EntityDAOException {
        prepareStatementForUpdate(userAddress, statement);
    }
}
