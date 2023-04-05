package com.bankati.dao.daoMySQL;

import com.bankati.dao.DaoFactory;
import com.bankati.dao.IDao;
import com.bankati.dao.exceptions.DaoConfigException;
import com.bankati.dao.exceptions.DaoException;
import com.bankati.modele.Client;
import com.bankati.modele.Credit;
import com.bankati.modele.Utilisateur;
import lombok.var;

import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MySqlSessionFactory extends DaoFactory {

    private static MySqlSessionFactory INSTANCE = null;
    private static Connection session = null;
    private static IDao<Utilisateur,Long> USER_DAO = null;
    private static IDao<Client,Long> CLIENT_DAO = null;
    private static IDao<Credit,Long> CREDIT_DAO = null;

    private MySqlSessionFactory() throws DaoConfigException{
        try {
            ClassLoader cl = Thread.currentThread().getContextClassLoader();
            var config = cl.getResourceAsStream("application.properties");

            if (config == null) throw new IOException("fichier properties introuvable");
            Properties propertiesFile = new Properties();
            propertiesFile.load(config);

            var url = propertiesFile.getProperty("URL");
            var user = propertiesFile.getProperty("USERNAME");
            var pass = propertiesFile.getProperty("PASSWORD");

            session = DriverManager.getConnection(url,user,pass);
            System.out.println("Connexion établit avec succés");

            var creditDao = propertiesFile.getProperty("CREDITDAO");
            var clientDao = propertiesFile.getProperty("CLIENTDAO");
            var utilisateurDao = propertiesFile.getProperty("USERDAO");

            Class cCreditDao = Class.forName(creditDao);
            Class cClientDao = Class.forName(clientDao);
            Class cUserDao = Class.forName(utilisateurDao);

            CREDIT_DAO = (IDao<Credit, Long>)  cCreditDao.getDeclaredConstructor().newInstance();
            CLIENT_DAO = (IDao<Client, Long>)  cClientDao.getDeclaredConstructor().newInstance();
            USER_DAO = (IDao<Utilisateur, Long>) cUserDao.getDeclaredConstructor().newInstance();

            Method setFactory = cCreditDao.getMethod("setFactory", DaoFactory.class);
                   setFactory.invoke(CREDIT_DAO,this);

                   setFactory = cClientDao.getMethod("setFactory", DaoFactory.class);
                   setFactory.invoke(CREDIT_DAO,this);

                   setFactory = cUserDao.getMethod("setFactory", DaoFactory.class);
                   setFactory.invoke(CREDIT_DAO,this);

        } catch (Exception e) { throw new DaoConfigException(e.getMessage());}
    }
    public static MySqlSessionFactory getInstance(){
        if (INSTANCE == null) {
            try {
                INSTANCE = new MySqlSessionFactory();
            }
            catch (DaoConfigException e) { e.printStackTrace();}
        }
        return INSTANCE;
    }

    public static Connection getSession(){
        if (session == null) getInstance();
        return session;
    }

    @Override
    public IDao<Utilisateur, Long> getUtilisateurDao() {
        if (USER_DAO == null) getInstance();
        return USER_DAO;
    }

    @Override
    public IDao<Client, Long> getClientDao() {
        if (CLIENT_DAO == null) getInstance();
        return CLIENT_DAO;
    }

    @Override
    public IDao<Credit, Long> getCreditDao() {
        if (CREDIT_DAO == null) getInstance();
        return CREDIT_DAO;
    }

    public static void closeSession(){
        if (session != null){
            try {
                session.close();
                System.out.println("Fermeture de session avec succés");
            }
            catch (SQLException e ){
                System.err.println("Fermeture de session échoué");
            }
        }
    }


}
