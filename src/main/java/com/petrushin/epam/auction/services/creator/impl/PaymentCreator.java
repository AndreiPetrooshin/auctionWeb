package com.petrushin.epam.auction.services.creator.impl;

import com.petrushin.epam.auction.exceptions.CreatorException;
import com.petrushin.epam.auction.services.creator.AbstractCreator;
import com.petrushin.epam.auction.services.creator.Creator;
import com.petrushin.epam.auction.services.domain.FlowerLot;
import com.petrushin.epam.auction.services.domain.Payment;
import com.petrushin.epam.auction.services.domain.User;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Extends the {@link AbstractCreator} class
 * and creates the Payment entity
 *
 * @author Andrei Petrushin
 * @version 1.0.0
 */
public class PaymentCreator extends AbstractCreator<Payment> {

    private static final String COLUMN_PAYMENT_ID = "payment_id";
    private static final String COLUMN_PRICE = "price";
    private static final String COLUMN_IS_PAID = "is_paid";

    private Creator<User> userCreator;
    private Creator<FlowerLot> flowerLotCreator;

    public PaymentCreator(Creator<User> userCreator, Creator<FlowerLot> flowerLotCreator) {
        this.userCreator = userCreator;
        this.flowerLotCreator = flowerLotCreator;
    }

    @Override
    public Payment createEntity(ResultSet resultSet) throws CreatorException {
        try {
            Long id = resultSet.getLong(COLUMN_PAYMENT_ID);
            FlowerLot lot = flowerLotCreator.createEntity(resultSet);
            User user = userCreator.createEntity(resultSet);
            BigDecimal price = resultSet.getBigDecimal(COLUMN_PRICE);
            boolean isPaid = resultSet.getBoolean(COLUMN_IS_PAID);

            return new Payment(id, lot, user, price, isPaid);
        } catch (SQLException e) {
            throw new CreatorException("Error witch creation Payment entity" + e.getMessage(), e);
        }
    }
}
