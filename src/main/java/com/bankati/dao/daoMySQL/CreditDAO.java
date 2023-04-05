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
        List<Credit> credits = null;
        Connection session = factory.getSession();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String SQL = "SELECT * FROM credit";
        try {
            ps = Utilitaire.initPS(session,SQL,false);
            rs = ps.executeQuery();
            while (rs.next()) credits.add(map(rs));
            System.out.println("[SQL] : " + SQL);
            return credits;
        }
        catch (SQLException e ) { throw new DaoException(e.getMessage());}
        finally {
            Utilitaire.close(ps,rs);
        }
    }

    @Override
    public Credit save(Credit credit) throws DaoException{
        Connection session = factory.getSession();
        PreparedStatement ps = null;
        String SQL = "INSERT INTO credit (capital, nbrMois, taux, demandeur, mensualité) " +
                     "VALUES (?,?,?,?,?)";
        try {
            ps = Utilitaire.initPS(session, SQL,true
                    ,credit.getCapitale_Emprunt()
                    ,credit.getNombre_Mois()
                    ,credit.getTaux_Mensuel()
                    ,credit.getNom_Demandeur()
                    ,credit.getMensualite());
            var statut = ps.executeUpdate();
            if (statut == 0) throw new DaoException("0 credit inéré !!!");
            else {
                var rs = ps.getGeneratedKeys();
                var id = rs.getLong(1);
                credit.setId(id);
            }
            System.out.println("[SQL] : " + SQL);
            return credit;
        }
        catch (SQLException e) {throw new DaoException(e.getMessage());}
        finally {
            Utilitaire.close(ps);
        }
    }

    @Override
    public Credit update(Credit credit) throws DaoException{
        Connection session = factory.getSession();
        PreparedStatement ps = null;
        String SQL = "UPDATE credit set capital = ?, nbrMois = ?, demandeur = ?, mensualité = ? " +
                     "WHERE id = ?";
        try {
            ps = Utilitaire.initPS(session, SQL,false
                    ,credit.getCapitale_Emprunt()
                    ,credit.getNombre_Mois()
                    ,credit.getTaux_Mensuel()
                    ,credit.getNom_Demandeur()
                    ,credit.getMensualite());
            var statut = ps.executeUpdate();
            if (statut == 0) throw new DaoException("0 crédit modifié !!!");

            System.out.println("[SQL] : " + SQL);
            return credit;
        }
        catch (SQLException e) {throw new DaoException(e.getMessage());}
        finally {
            Utilitaire.close(ps);
        }
    }

    @Override
    public Boolean delete(Credit credit) throws DaoException{
        Connection session = factory.getSession();
        PreparedStatement ps = null;
        String SQL = "DELETE FROM credit WHERE id = ?";
        try {
            ps = Utilitaire.initPS(session, SQL, false, credit.getId());
            var statut = ps.executeUpdate();
            if (statut == 0) throw new DaoException("0 crédit supprimé !!!");
            System.out.println("[SQL] : " + SQL);
            return true;
        }
        catch (SQLException e) {throw new DaoException(e.getMessage());}
        finally {
            Utilitaire.close(ps);
        }    }

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
