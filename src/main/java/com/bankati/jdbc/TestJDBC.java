package com.bankati.jdbc;

import com.bankati.modele.Client;
import com.bankati.modele.Credit;
import lombok.var;


import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class TestJDBC {
    public static void main(String[] args) {

        Connection connection = Singleton.getSession();
        var credits = new ArrayList<Credit>();
        try {
            var ps = connection.prepareStatement(
                    "SELECT * FROM credit cr, client cl, utilisateur u " +
                            "WHERE cr.demandeur = cl.id and " +
                            "cl.id = u.id and " +
                            "cr.capital = ?");
            ps.setDouble(1,30000.0);
            var rs = ps.executeQuery();
            while (rs.next()){
                var id = rs.getLong("id");
                var capital = rs.getDouble("capital");
                var nbrMois = rs.getInt("nbrMois");
                var taux = rs.getDouble("taux");
                var nomdemandeur = rs.getString("nom");
                var prenomdemandeur = rs.getString("prenom");
                var mensualite = rs.getDouble("mensualité");

                var client = new Client();
                client.setNomComplet(prenomdemandeur,nomdemandeur);
                credits.add(new Credit(id,capital,nbrMois,taux,client,mensualite));
            }
            if (credits.isEmpty()) throw new SQLException("Aucun crédit trouvé");
            else credits.forEach(System.out::println);
        }
        catch (SQLException e) {
            System.err.println("Connexion échoué !!");
        }

        Singleton.closeSession();
    }
}