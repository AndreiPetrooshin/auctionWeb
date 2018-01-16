package com.petrushin.epam.auction.services;

import com.petrushin.epam.auction.exceptions.EntityDAOException;
import com.petrushin.epam.auction.services.dao.impl.PaymentDao;
import com.petrushin.epam.auction.services.domain.Payment;
import com.petrushin.epam.auction.services.domain.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Service class which do the main business logic
 * with {@link Payment} entity
 *
 * @author Andrei Petrushin
 * @version 1.0.0
 */
public class PaymentService implements Service<Payment> {

    private PaymentDao paymentDao;

    public PaymentService(PaymentDao paymentDao) {
        this.paymentDao = paymentDao;
    }

    @Override
    public Payment findById(Long id) throws EntityDAOException {
        return paymentDao.findById(id);
    }

    public List<Payment> getByUserId(Long id) throws EntityDAOException {
        return paymentDao.getByUserId(id);
    }

    @Override
    public List<Payment> getAll() throws EntityDAOException {
        return paymentDao.getAll();
    }

    @Override
    public boolean save(Payment payment) throws EntityDAOException {
        return paymentDao.save(payment);
    }

    @Override
    public boolean delete(Long id) throws EntityDAOException {
        return paymentDao.delete(id);
    }

    @Override
    public boolean update(Payment payment) throws EntityDAOException {
        return paymentDao.update(payment);
    }
}
