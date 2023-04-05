package com.bankati.dao.daoMySQL;

import com.bankati.dao.IDao;
import com.bankati.dao.exceptions.DaoException;
import com.bankati.modele.Credit;
import com.bankati.modele.Utilisateur;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.var;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor
public class CreditDAO implements IDao<Credit,Long> {

    MySqlSessionFactory factory;

    @Override
    public Credit findById(Long idCredit) throws DaoException{
        Credit credit = null;
        Connection session = factory.getSession();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String SQL = "SELECT * FROM credit WHERE id = ?";
        try {
            ps = Utilitaire.initPS(session,SQL,false,idCredit);
            rs = ps.executeQuery();
            if (rs.next()) credit = map(rs);
            System.out.println("[SQL] : " + SQL);
            return credit;
        }
        catch (SQLException e ) { throw new DaoException(e.getMessage());}
        finally {
            Utilitaire.close(ps,rs);
        }
    }

    @Override
    public List<Credit> findall() throws DaoException{
        return null;
    }

    @Override
    public Credit save(Credit credit) throws DaoException{
        return null;
    }

    @Override
    public Credit update(Credit credit) throws DaoException{
        return null;
    }

    @Override
    public Boolean delete(Credit credit) throws DaoException{
        return null;
    }

    @Override
    public Boolean deleteById(Long aLong) throws DaoException{
        return null;
    }

    public Credit map(ResultSet rs) throws DaoException{
        try {
            long id = rs.getLong("id");
            double capital = rs.getDouble("capital");
            int nbrMois = rs.getInt("nbrMoiis");
            double taux = rs.getDouble("taux");
            int idClient = rs.getInt("demandeur");
            double mensualité = rs.getDouble("mensualité");

            var client = factory.getClientDao().findById((long)idClient);

            return new Credit(id,capital,nbrMois,taux,client,mensualité);
        }
        catch (SQLException e) { throw new DaoException(e.getMessage());}
    }
}
