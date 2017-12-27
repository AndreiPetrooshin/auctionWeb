package com.petrushin.services;

import com.petrushin.exceptions.EntityDAOException;

import java.util.List;

public interface Service <T> {

    T findById(Long id) throws EntityDAOException;

    List<T> getAll() throws EntityDAOException;

    boolean save(T t) throws EntityDAOException;

    boolean delete(Long id) throws EntityDAOException;

    boolean update(T t) throws EntityDAOException;


}