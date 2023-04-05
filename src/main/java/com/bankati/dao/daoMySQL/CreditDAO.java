package com.bankati.dao.daoMySQL;

import com.bankati.dao.IDao;
import com.bankati.dao.exceptions.DaoException;
import com.bankati.modele.Credit;

import java.util.List;

public class CreditDAO implements IDao<Credit,Long> {
    @Override
    public Credit findById(Long idCredit) {
        return null;
    }

    @Override
    public List<Credit> findall() {
        return null;
    }

    @Override
    public Credit save(Credit credit) {
        return null;
    }

    @Override
    public Credit update(Credit credit) {
        return null;
    }

    @Override
    public Boolean delete(Credit credit) {
        return null;
    }

    @Override
    public Boolean deleteById(Long aLong) {
        return null;
    }
}
