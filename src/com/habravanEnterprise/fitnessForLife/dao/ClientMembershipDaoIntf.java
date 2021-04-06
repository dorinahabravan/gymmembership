package com.habravanEnterprise.fitnessForLife.dao;

import com.habravanEnterprise.fitnessForLife.models.ClientMembership;
import java.util.List;

/**
 *
 * @author Dorina
 */
public interface ClientMembershipDaoIntf {

    boolean save(ClientMembership clientMemb) throws Exception;
    boolean update(ClientMembership clientMemb) throws Exception;
    boolean  delete(ClientMembership clientMemb) throws Exception;

    ClientMembership findByClientId(int clientID) throws Exception;
   List< ClientMembership> findByMembershipId(int gymID) throws Exception;

     List<ClientMembership>  findAll() throws Exception;

}
