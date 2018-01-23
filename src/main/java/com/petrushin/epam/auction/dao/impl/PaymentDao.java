package com.petrushin.epam.auction.dao.impl;


import com.petrushin.epam.auction.dao.AbstractDao;
import com.petrushin.epam.auction.dao.ConnectionPool;
import com.petrushin.epam.auction.dao.creator.Creator;
import com.petrushin.epam.auction.domain.FlowerLot;
import com.petrushin.epam.auction.domain.Payment;
import com.petrushin.epam.auction.domain.User;
import com.petrushin.epam.auction.exceptions.CreatorException;
import com.petrushin.epam.auction.exceptions.EntityDAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Dao class witch extends {@link AbstractDao} and implements methods for
 * {@link com.petrushin.epam.auction.domain.Payment} entity
 *
 * @author Andrei Petrushin
 * @version 1.0.0
 */
public class PaymentDao extends AbstractDao<Payment> {

    private static final Logger LOGGER = LogManager.getLogger(PaymentDao.class);


    public PaymentDao(Creator<Payment> creator) {
        super(creator);
    }

    @Override
    public Payment findById(Long id) throws EntityDAOException {
        return getByPK(id, Payment.GET_BY_ID);
    }

    @Override
    public List<Payment> getAll() throws EntityDAOException {
        return getAll(Payment.GET_ALL);
    }

    @Override
    public boolean save(Payment payment) throws EntityDAOException {
        return save(payment, Payment.ADD_PAYMENT);
    }

    @Override
    public boolean delete(Long id) throws EntityDAOException {
        return delete(id, Payment.DELETE_BY_ID);
    }

    @Override
    public boolean update(Payment payment) throws EntityDAOException {
        return update(payment, Payment.UPDATE_PAYMENT);
    }

    public List<Payment> getByUserId(Long id) throws EntityDAOException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(Payment.GET_BY_USER_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            return creator.createEntityList(resultSet);
        } catch (SQLException | CreatorException e) {
            LOGGER.error("Error with getByUserId operation", e);
            throw new EntityDAOException(e.getMessage(), e);
        } finally {
            ConnectionPool.getInstance().returnConnection(connection);
        }
    }

    public Payment getByLotId(Long id) throws EntityDAOException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(Payment.GET_BY_LOT_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            Payment payment = null;
            if (resultSet.next()) {
                payment = creator.createEntity(resultSet);
            }
            return payment;
        } catch (SQLException | CreatorException e) {
            LOGGER.error("Error with getByLotId operation", e);
            throw new EntityDAOException(e.getMessage(), e);
        } finally {
            ConnectionPool.getInstance().returnConnection(connection);
        }
    }

    @Override
    protected void prepareStatementForUpdate(Payment payment, PreparedStatement statement)
            throws EntityDAOException {
        try {
            FlowerLot lot = payment.getLot();
            Long lotId = lot.getId();
            statement.setLong(1, lotId);

            User user = payment.getUser();
            Long userId = user.getId();
            statement.setLong(2, userId);

            BigDecimal price = payment.getPrice();
            statement.setBigDecimal(3, price);

            boolean isPaid = payment.isPaid();
            statement.setBoolean(4, isPaid);

            Long id = payment.getId();
            statement.setLong(5, id);
        } catch (SQLException e) {
            throw new EntityDAOException("Error with prepare statement for payment. " + e.getMessage(), e);
        }

    }

    @Override
    protected void prepareStatementForInsert(Payment payment, PreparedStatement statement)
            throws EntityDAOException {
        prepareStatementForUpdate(payment, statement);
    }
}
