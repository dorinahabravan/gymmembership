package com.habravanEnterprise.fitnessForLife.services.impl;

import com.habravanEnterprise.fitnessForLife.models.Client;
import com.habravanEnterprise.fitnessForLife.services.ClientMockIntf;
import java.util.ArrayList;
import java.util.List;

public class ClientMockImpl implements ClientMockIntf {

    List<Client> listaClienti;

    public ClientMockImpl(List<Client> listaClienti) {
        this.listaClienti = listaClienti;
    }

    public ClientMockImpl() {
        listaClienti = new ArrayList<>();
    }

    @Override
    public void save(Client client) {
        listaClienti.add(client);
    }

    @Override
    public void update(Client client) {
        for (int i = 0; i < listaClienti.size(); i++) {

            if (listaClienti.get(i).getCustomerid() == client.getCustomerid()) {
                listaClienti.set(i, client);

            }
        }
    }

    @Override
    public void delete(Client client) {

        for (int i = 0; i < listaClienti.size(); i++) {
            if (listaClienti.get(i).getCustomerid() == client.getCustomerid()) {
                listaClienti.remove(i);
                break;

            }

        }

    }

    @Override
    public Client findById(int customerid) {

        Client client = null;
        for (int i = 0; i < listaClienti.size(); i++) {
            if (listaClienti.get(i).getCustomerid() == customerid) {
                client = listaClienti.get(i);
                break;
            }

        }

        return client;
    }

    @Override
    public List<Client> findAll() {
        return listaClienti;
    }

    @Override
    public List<Client> findByName(String fullname) {
        List<Client> list = new ArrayList<>();
        for (int i = 0; i < listaClienti.size(); i++) {
            if (listaClienti.get(i).getFullname().equals(fullname)) {
                Client client = listaClienti.get(i);
                list.add(client);
            }
        }
        return list;

    }

}
