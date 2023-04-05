package com.bankati.modele;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class Utilisateur {
    protected Long id;
    protected String login, motDePass, nom, prenom;
    protected Role role;

    public void setNomComplet(String prenom, String nom){
        setPrenom(prenom);
        setNom(nom);
    }
    public String nomComplet(){
        return prenom + " " + nom;
    }
    public String toString(){
        return prenom + " " + nom;
    }

}
