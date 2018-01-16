package com.petrushin.epam.auction.services.creator;

import com.petrushin.epam.auction.exceptions.CreatorException;

import java.sql.ResultSet;
import java.util.List;

/**
 * This Interface inherits all classes witch creates the
 * Entities with type T;
 */
public interface Creator<T> {

    /**
     * Creates the Entity by ResultSet
     *
     * @return Entity
     */
    T createEntity(ResultSet resultSet)
            throws CreatorException;

    /**
     * Create the list of Entities
     *
     * @return list of entities
     */
    List<T> createEntityList(ResultSet resultSet)
            throws CreatorException;
}
