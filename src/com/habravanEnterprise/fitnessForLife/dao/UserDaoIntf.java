package com.habravanEnterprise.fitnessForLife.dao;

import com.habravanEnterprise.fitnessForLife.models.User;
import java.util.List;

/**
 *
 * @author Dorina
 */
public interface UserDaoIntf {

    boolean save(User user) throws Exception;
    boolean update(User user) throws Exception;
    boolean delete(User user) throws Exception;

    User findById(int idUser) throws Exception;

    List<User> findByUsername(String username) throws Exception;

    List<User> findAll() throws Exception;
}
