package com.petrushin.dao;

import com.petrushin.dao.exception.EntityDAOException;

import java.util.List;

public interface EntityDAO<T> {

    T findById(int id) throws EntityDAOException;

    List<T> getAll() throws EntityDAOException;

    void add(T t) throws EntityDAOException;
}
