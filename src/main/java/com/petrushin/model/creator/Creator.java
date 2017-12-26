package com.petrushin.model.creator;

import com.petrushin.exceptions.CreatorException;

import java.sql.ResultSet;
import java.util.List;

public interface Creator<T> {

    T createEntity(ResultSet resultSet)
            throws CreatorException;

    List<T> createEntityList(ResultSet resultSet)
            throws CreatorException;
}
