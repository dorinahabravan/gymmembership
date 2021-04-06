
package com.habravanEnterprise.fitnessForLife.dao;

import com.habravanEnterprise.fitnessForLife.models.Membership;
import java.util.List;

/**
 *
 * @author Dorina
 */
public interface MembershipDaoIntf {
    
    boolean save (Membership membership) throws Exception;
    boolean update (Membership membership) throws Exception;
    boolean delete (Membership membership) throws Exception;
    
    Membership findByMembershipID(int membershipId) throws Exception;
    List<Membership> findByMembershipType(String memmbershipT) throws Exception;
    
    List <Membership> findAll() throws Exception;
    
    
    
}
