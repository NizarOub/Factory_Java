package com.bankati.dao;

import com.bankati.dao.daoMySQL.MySqlSessionFactory;
import com.bankati.modele.Client;
import com.bankati.modele.Credit;
import com.bankati.modele.Utilisateur;

public abstract class DaoFactory {

    public static final
        int MYSQL_DATA_UNIT = 1,
        FILE_DATA_UNIT = 2,
        InMEMORY_DATA_UNIT = 3;

    public abstract IDao<Utilisateur,Long> getUtilisateurDao();
    public abstract IDao<Client,Long> getClientDao();
    public abstract IDao<Credit,Long> getCreditDao();

    public static final DaoFactory getDaoFactory(int factoryType){
        switch (factoryType){
            case 1 : return MySqlSessionFactory.getInstance();
            default : return null;
        }
    }
}
