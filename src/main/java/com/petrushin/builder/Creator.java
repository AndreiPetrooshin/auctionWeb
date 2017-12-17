package com.petrushin.builder;

import com.petrushin.exceptions.CreatorException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public interface Creator<T> {

    void initStatement(T t, PreparedStatement statement)
            throws CreatorException;

    T createEntity(ResultSet resultSet)
            throws CreatorException;

    List<T> createEntityList(ResultSet resultSet)
            throws CreatorException;
}
