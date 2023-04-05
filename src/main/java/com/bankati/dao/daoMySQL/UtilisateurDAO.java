package com.bankati.dao.daoMySQL;

import com.bankati.dao.IDao;
import com.bankati.modele.Utilisateur;

import java.util.List;

public class UtilisateurDAO implements IDao<Utilisateur,Long> {
    @Override
    public Utilisateur findById(Long id) {
        return null;
    }

    @Override
    public List<Utilisateur> findall() {
        return null;
    }

    @Override
    public Utilisateur save(Utilisateur utilisateur) {
        return null;
    }

    @Override
    public Utilisateur update(Utilisateur utilisateur) {
        return null;
    }

    @Override
    public Boolean delete(Utilisateur utilisateur) {
        return null;
    }

    @Override
    public Boolean deleteById(Long aLong) {
        return null;
    }
}
