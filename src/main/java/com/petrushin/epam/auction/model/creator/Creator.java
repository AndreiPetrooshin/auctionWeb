package com.petrushin.epam.auction.model.creator;

import com.petrushin.epam.auction.exceptions.CreatorException;

import java.sql.ResultSet;
import java.util.List;

public interface Creator<T> {

    T createEntity(ResultSet resultSet)
            throws CreatorException;

    List<T> createEntityList(ResultSet resultSet)
            throws CreatorException;
}
