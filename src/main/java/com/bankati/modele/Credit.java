package com.bankati.modele;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.var;

@Data @AllArgsConstructor @NoArgsConstructor
public class Credit {
    private Long id;
    private Double capitale_Emprunt;
    private Integer nombre_Mois;
    private Double taux_Mensuel;
    private Client  nom_Demandeur;
    private Double mensualite;


    @Override
    public String toString(){
        var creditStr = "==================================================================== \n";
        creditStr+= "=> Crédit n°                  : " +getId()+ "                        \n";
        creditStr+= "=> Nom du demandeur de crédit : " +  nom_Demandeur.nomComplet() + "           \n";
        creditStr+= "-------------------------------------------------------------------- \n";
        creditStr+= "=> Capitale Emprunté          : " + getCapitale_Emprunt() + "        \n";
        creditStr+= "=> Nombre de mois             : " + getNombre_Mois() + "             \n";
        creditStr+= "=> Taux mensuel               : " + getTaux_Mensuel() + "            \n" ;
        creditStr+= "-------------------------------------------------------------------- \n";
        creditStr+= "=> Mensualité                 : "
                + (getMensualite() == 0.0 ? "NON-CALCULE":getMensualite()+ " DH/mois")+"\n";
        creditStr+= "==================================================================== \n";

        return creditStr;
    }
}
