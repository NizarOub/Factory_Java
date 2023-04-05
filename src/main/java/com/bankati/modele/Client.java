package com.bankati.modele;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class Client extends Utilisateur{
    private String email, cin, tel, adresse;
    private Sexe sexe;
}
