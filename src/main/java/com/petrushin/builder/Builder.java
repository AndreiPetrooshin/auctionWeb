package com.petrushin.builder;

import com.petrushin.builder.exceptions.AbstractBuilderException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public interface Builder<T> {

    void initStatement(T t, PreparedStatement statement)
            throws AbstractBuilderException;

    T createEntity(ResultSet resultSet)
            throws AbstractBuilderException;

    List<T> createEntityList(ResultSet resultSet)
            throws AbstractBuilderException;
}
