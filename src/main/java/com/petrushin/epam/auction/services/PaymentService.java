package com.petrushin.epam.auction.services;

import com.petrushin.epam.auction.exceptions.ServiceException;
import com.petrushin.epam.auction.model.dao.impl.PaymentDaoImpl;
import com.petrushin.epam.auction.model.domain.Payment;
import com.petrushin.epam.auction.model.domain.User;

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

    private PaymentDaoImpl paymentDao;

    public PaymentService(PaymentDaoImpl paymentDao) {
        this.paymentDao = paymentDao;
    }

    @Override
    public Payment findById(Long id) throws ServiceException {
        return paymentDao.findById(id);
    }

    public List<Payment> getByUserId(Long id) throws ServiceException {
        List<Payment> payments = getAll();
        List<Payment> result = new ArrayList<>();
        for (Payment payment : payments) {
            User user = payment.getUser();
            Long userId = user.getId();
            if (Objects.equals(userId, id)) {
                result.add(payment);
            }
        }
        return result;
    }

    @Override
    public List<Payment> getAll() throws ServiceException {
        return paymentDao.getAll();
    }

    @Override
    public boolean save(Payment payment) throws ServiceException {
        return paymentDao.save(payment);
    }

    @Override
    public boolean delete(Long id) throws ServiceException {
        return paymentDao.delete(id);
    }

    @Override
    public boolean update(Payment payment) throws ServiceException {
        return paymentDao.update(payment);
    }
}
