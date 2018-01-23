package com.petrushin.epam.auction.services;

import com.petrushin.epam.auction.exceptions.EntityDAOException;
import com.petrushin.epam.auction.dao.impl.PaymentDao;
import com.petrushin.epam.auction.domain.Payment;

import java.util.List;

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

    public Payment getByLotId(Long id) throws EntityDAOException {
        return paymentDao.getByLotId(id);
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
