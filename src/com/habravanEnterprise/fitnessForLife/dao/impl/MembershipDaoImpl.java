package com.habravanEnterprise.fitnessForLife.dao.impl;

import com.habravanEnterprise.fitnessForLife.dao.MembershipDaoIntf;
import com.habravanEnterprise.fitnessForLife.db.SingletonJDBCDataSource;
import com.habravanEnterprise.fitnessForLife.models.Membership;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author Dorina
 */
public class MembershipDaoImpl implements MembershipDaoIntf {

    private static final Logger LOG = Logger.getLogger(UserDaoImpl.class.getName());
    private SingletonJDBCDataSource dataSource = SingletonJDBCDataSource.getInstance();

    @Override
    public boolean save(Membership membership) throws Exception {

        String sql = "INSERT INTO membership VALUES( ?, ?, ?, ?)";
        //                       Pozitia           1  2   3 4
        try {
            Connection conn = dataSource.getConnection();
            PreparedStatement pstat = conn.prepareStatement(sql);

            pstat.setInt(1, membership.getMembershipID());
            pstat.setString(2, membership.getMembershipType());
            pstat.setString(3, membership.getMembershipPeriod());
            pstat.setString(4, membership.getInstructorName());

            int modifications = pstat.executeUpdate();
            if (modifications > 0) {

                //throw new SQLException("No changes were made SAVE to membership=" + membership.getMembershipType());
                return true;
            }

        } catch (Exception e) {
            LOG.severe(e.toString());
            //throw e;
            return false;

        }
        return true;
    }

    @Override
    public boolean update(Membership membership) throws Exception {

        String sql = "UPDATE membership SET  Membership_Type=? , Membership_Period=? , Instructor_Name=? WHERE ID_Membership=? ";
        try {
            Connection conn = dataSource.getConnection();
            PreparedStatement pstat = conn.prepareStatement(sql);

            pstat.setString(1, membership.getMembershipType());
            pstat.setString(2, membership.getMembershipPeriod());
            pstat.setString(3, membership.getInstructorName());

            pstat.setInt(4, membership.getMembershipID());

            int modifications = pstat.executeUpdate();

            if (modifications > 0) {
                //throw new SQLException("No changes were made UPDATE to membership=" + membership.getMembershipType());
                return true;
            }

        } catch (Exception e) {

            LOG.severe(e.toString());
            //throw e;
            return true;

        }
        return false;
    }

    @Override
    public boolean delete(Membership membership) throws Exception {
        String sql = "DELETE FROM membership WHERE ID_Membership=?";

        try {
            Connection conn = dataSource.getConnection();
            PreparedStatement pstat = conn.prepareStatement(sql);

            pstat.setInt(1, membership.getMembershipID());

            int modifications = pstat.executeUpdate();
            if (modifications > 0) {
                //throw new SQLException("No changes were made DELETE to membership=" + membership.getMembershipID());
                return true;
            }

        } catch (Exception e) {
            LOG.severe(e.toString());
            //throw e;

        }
        return true;
    }

    @Override
    public Membership findByMembershipID(int membershipId) throws Exception {
        Membership membership = null;

        String sql = "SELECT * FROM membership  WHERE ID_Membership=?";

        ResultSet rs = null;

        try {
            Connection conn = dataSource.getConnection();
            PreparedStatement pstat = conn.prepareStatement(sql);

            pstat.setInt(1, membershipId);

            rs = pstat.executeQuery();

            if (rs.next()) {
                int membershipID = rs.getInt(1);

                String membershipType = rs.getString(2);
                String membeershipPeriod = rs.getString(3);
                String instructorName = rs.getString(4);

                membership = new Membership(membershipID, membershipType, membeershipPeriod, instructorName);

            }
        } catch (Exception e) {
            LOG.severe(e.toString());
            //throw new SQLException(e.getMessage());

        } finally {
            if (rs != null) {
                rs.close();

            }

        }
        return membership;

    }

    @Override
    public List<Membership> findByMembershipType(String membershipT) throws Exception {
        List<Membership> list = new ArrayList<>();
        
        Membership membership = null;

        String sql = "SELECT * FROM membership  WHERE Membership_Type=?";

        ResultSet rs = null;

        try {
            Connection conn = dataSource.getConnection();
            PreparedStatement pstat = conn.prepareStatement(sql);

            pstat.setString(1, membershipT);

            rs = pstat.executeQuery();

            if (rs.next()) {

                int membershipID = rs.getInt(1);

                String membershipType = rs.getString(2);
                String membeershipPeriod = rs.getString(3);
                String instructorName = rs.getString(4);

                membership = new Membership(membershipID, membershipType, membeershipPeriod, instructorName);
                list.add(membership);

            }
        } catch (Exception e) {
            LOG.severe(e.toString());
            // throw new SQLException(e.getMessage());

        } finally {
            if (rs != null) {
                rs.close();

            }

        }
        return list;

    }

    @Override
    public List<Membership>  findAll() throws Exception {

        List<Membership> list = new ArrayList<>();

        String sql = "SELECT * FROM membership";

        try (
                Connection conn = dataSource.getConnection();
                Statement stat = conn.createStatement();
                ResultSet rs = stat.executeQuery(sql);) {
            while (rs.next()) {

                int membershipID = rs.getInt(1);
                String membershipType = rs.getString(2);
                String membershipPeriod = rs.getString(3);
                String instructorName = rs.getString(4);

                Membership membership = new Membership(membershipID, membershipType, membershipPeriod, instructorName);
                list.add(membership);

            }
        } catch (SQLException ex) {
            LOG.severe(ex.toString());
            // throw new SQLException(ex.getMessage());
            

        }

        return list;

    }

}
