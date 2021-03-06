package com.petrushin.epam.auction.dao.creator;

import com.petrushin.epam.auction.exceptions.CreatorException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implements the {@link Creator} interface
 *
 * @author Andrei Petrushin
 * @version 1.0.0
 */
public abstract class AbstractCreator<T> implements Creator<T> {


    public abstract T createEntity(ResultSet resultSet)
            throws CreatorException;

    public List<T> createEntityList(ResultSet resultSet)
            throws CreatorException {
        List<T> list = new ArrayList<>();
        try {
            while (resultSet.next()) {
                T t = createEntity(resultSet);
                list.add(t);
            }
            return list;
        } catch (SQLException | CreatorException e) {
            throw new CreatorException(
                    "Error with entity list creation " + e.getMessage(), e);
        }
    }


}
