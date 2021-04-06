package com.habravanEnterprise.fitnessForLife.dao;

import com.habravanEnterprise.fitnessForLife.models.GymDetails;
import java.util.List;

/**
 *
 * @author Dorina
 */
public interface GymDetailsDaoIntf {

    boolean save(GymDetails gymDetails) throws Exception;
    boolean update(GymDetails gymDetails) throws Exception;
    boolean delete(GymDetails gymDetails) throws Exception;

    GymDetails findByIdGym(int idGym) throws Exception;
    List<GymDetails> findByGymLocation(String location) throws Exception;

    List<GymDetails> findAll() throws Exception;

}
