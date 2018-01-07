package com.petrushin.epam.auction.services;

import com.petrushin.epam.auction.exceptions.ServiceException;

import java.util.List;

public interface Service<T> {

    T findById(Long id) throws ServiceException;

    List<T> getAll() throws ServiceException;

    boolean save(T t) throws ServiceException;

    boolean delete(Long id) throws ServiceException;

    boolean update(T t) throws ServiceException;


}
