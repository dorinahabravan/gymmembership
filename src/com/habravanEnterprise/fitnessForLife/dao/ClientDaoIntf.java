package com.habravanEnterprise.fitnessForLife.dao;

import com.habravanEnterprise.fitnessForLife.models.Client;
import java.util.List;

/**
 *
 * @author Dorina
 */
public interface ClientDaoIntf {

    boolean save(Client client) throws Exception;
    boolean update(Client client) throws Exception;
    boolean delete(Client client) throws Exception;

    Client findById(int idClient) throws Exception;
    Client findByUserId(int idUser) throws Exception;
    List<Client> findByFullname(String fullname) throws Exception;

    List<Client> findAll() throws Exception;

}
