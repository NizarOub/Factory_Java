package com.bankati.dao.daoMySQL;

import com.bankati.dao.IDao;
import com.bankati.modele.Client;
import com.bankati.modele.Utilisateur;

import java.util.List;

public class ClientDAO implements IDao<Client,Long> {
    @Override
    public Client findById(Long id) {
        return null;
    }

    @Override
    public List<Client> findall() {
        return null;
    }

    @Override
    public Client save(Client client) {
        return null;
    }

    @Override
    public Client update(Client client) {
        return null;
    }

    @Override
    public Boolean delete(Client client) {
        return null;
    }

    @Override
    public Boolean deleteById(Long aLong) {
        return null;
    }
}
