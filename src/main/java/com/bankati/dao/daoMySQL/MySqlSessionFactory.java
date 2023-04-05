package com.bankati.dao.daoMySQL;

import com.bankati.dao.DaoFactory;
import com.bankati.dao.IDao;
import com.bankati.modele.Client;
import com.bankati.modele.Credit;
import com.bankati.modele.Utilisateur;

import java.sql.Connection;

public class MySqlSessionFactory extends DaoFactory {

    private static MySqlSessionFactory INSTANCE = null;
    private static Connection session = null;
    private static IDao<Utilisateur,Long> USER_DAO = null;
    private static IDao<Client,Long> CLIENT_DAO = null;
    private static IDao<Credit,Long> CREDIT_DAO = null;

    @Override
    public IDao<Utilisateur, Long> getUtilisateurDao() {
        return null;
    }

    @Override
    public IDao<Client, Long> getClientDao() {
        return null;
    }

    @Override
    public IDao<Credit, Long> getCreditDao() {
        return null;
    }
}
