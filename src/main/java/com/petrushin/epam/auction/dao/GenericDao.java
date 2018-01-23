package com.petrushin.epam.auction.dao;

import com.petrushin.epam.auction.exceptions.EntityDAOException;
import com.petrushin.epam.auction.domain.Identified;

import java.sql.Connection;
import java.util.List;

public interface GenericDao<T extends Identified> {

    T findById(Long id) throws EntityDAOException;

    List<T> getAll() throws EntityDAOException;

    boolean save(T t) throws EntityDAOException;

    boolean delete(Long id) throws EntityDAOException;

    boolean update(T t) throws EntityDAOException;

    void rollback(Connection connection) throws EntityDAOException;

}
