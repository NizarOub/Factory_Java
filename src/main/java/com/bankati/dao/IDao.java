package com.bankati.dao;

import com.bankati.dao.exceptions.DaoException;

import java.util.List;

public interface IDao<T,ID> {
    T findById(ID id) throws DaoException;
    List<T> findall() throws DaoException;

    T save(T t) throws DaoException;
    T update(T t) throws DaoException;
    Boolean delete(T t) throws DaoException;
    Boolean deleteById(ID id) throws DaoException;

}
