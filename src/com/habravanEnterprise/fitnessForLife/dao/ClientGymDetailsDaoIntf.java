package com.habravanEnterprise.fitnessForLife.dao;


import com.habravanEnterprise.fitnessForLife.models.ClientGymDetails;
import java.util.List;

/**
 *
 * @author Dorina
 */
public interface ClientGymDetailsDaoIntf {

    boolean  save(ClientGymDetails clientGym)throws Exception;
    boolean update(ClientGymDetails clientGym)throws Exception;
    boolean delete(ClientGymDetails clientGym)throws Exception;

    List<ClientGymDetails> findByClientId(int clientID) throws Exception;
    List<ClientGymDetails> findByGymId(int gymID) throws Exception;

    List <ClientGymDetails> findAll() throws Exception;

   

}
