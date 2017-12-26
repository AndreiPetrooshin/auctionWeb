package com.petrushin.model.dao;

import com.petrushin.exceptions.EntityDAOException;

import java.sql.Connection;
import java.util.List;

public interface GenericDao<T> {

    T findById(Long id) throws EntityDAOException;

    List<T> getAll() throws EntityDAOException;

    boolean save(T t) throws EntityDAOException;

    boolean delete(Long id) throws EntityDAOException;

    boolean update(T t) throws EntityDAOException;

    void rollback(Connection connection) throws EntityDAOException;

}
