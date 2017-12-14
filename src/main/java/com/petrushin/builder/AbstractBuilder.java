package com.petrushin.builder;

import com.petrushin.builder.exceptions.AbstractBuilderException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractBuilder<T> {

    public abstract void initStatement(T t, PreparedStatement statement) throws AbstractBuilderException;

    public abstract T createEntity(ResultSet resultSet) throws AbstractBuilderException;

    public List<T> createEntityList(ResultSet resultSet) throws AbstractBuilderException {
        List<T> list = new ArrayList<T>();
        try {
            while (resultSet.next()) {
                T t = createEntity(resultSet);
                list.add(t);
            }
            return list;
        } catch (SQLException | AbstractBuilderException e) {
            throw new AbstractBuilderException("Error with entity list creation " + e.getMessage());
        }
    }


}
