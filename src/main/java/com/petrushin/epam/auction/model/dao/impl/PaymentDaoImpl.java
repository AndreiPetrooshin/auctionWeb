package com.petrushin.epam.auction.model.dao.impl;


import com.petrushin.epam.auction.exceptions.EntityDAOException;
import com.petrushin.epam.auction.model.creator.Creator;
import com.petrushin.epam.auction.model.dao.AbstractDao;
import com.petrushin.epam.auction.model.domain.FlowerLot;
import com.petrushin.epam.auction.model.domain.Payment;
import com.petrushin.epam.auction.model.domain.User;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Dao class witch extends {@link AbstractDao} and implements methods for
 * {@link com.petrushin.epam.auction.model.domain.Payment} entity
 *
 * @author Andrei Petrushin
 * @version 1.0.0
 */
public class PaymentDaoImpl extends AbstractDao<Payment> {


    public PaymentDaoImpl(Creator<Payment> creator) {
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
