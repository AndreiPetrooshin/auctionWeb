package com.petrushin.dao;

import com.petrushin.dao.exception.AbstractDAOException;

import java.util.List;

public interface GenericDAO<T> {

    T findById(Long id) throws AbstractDAOException;

    List<T> getAll() throws AbstractDAOException;

    boolean add(T t) throws AbstractDAOException;

    boolean delete(Long id) throws AbstractDAOException;

    boolean update(T t) throws AbstractDAOException;

}
