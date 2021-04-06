package com.habravanEnterprise.fitnessForLife.services;

import com.habravanEnterprise.fitnessForLife.models.Client;
import java.util.List;

public interface ClientMockIntf {

    void save(Client client);

    void update(Client client);

    void delete(Client client);

    List<Client> findByName(String fullname);

    Client findById(int customerid);

    List<Client> findAll();

}
