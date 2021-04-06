package com.habravanEnterprise.fitnessForLife.dao.impl;

import com.habravanEnterprise.fitnessForLife.dao.GymDetailsDaoIntf;
import com.habravanEnterprise.fitnessForLife.db.SingletonJDBCDataSource;
import com.habravanEnterprise.fitnessForLife.models.GymDetails;
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
public class GymDetailsDaoImpl implements GymDetailsDaoIntf {

    private static final Logger LOG = Logger.getLogger(GymDetailsDaoImpl.class.getName());
    private SingletonJDBCDataSource dataSource = SingletonJDBCDataSource.getInstance();

    @Override
    public boolean save(GymDetails gymDetails) throws Exception {

        String sql = "INSERT INTO gym_details VALUES (? ,? ,? ,?)";
        //                     Pozitia                1  2  3  4

        try {
            // Aici este varianta cu Prepared Statement 
            Connection conn = dataSource.getConnection();
            PreparedStatement pstat = conn.prepareStatement(sql);

            pstat.setInt(1, gymDetails.getGymId());
            pstat.setString(2, gymDetails.getGymName());
            pstat.setString(3, gymDetails.getGymLocation());
            pstat.setInt(4, gymDetails.getGymContactNumber());

            int modifications = pstat.executeUpdate();
            if (modifications > 0) {

                //throw new SQLException("No changes were made SAVE to gym_details=" + gymDetails.getGymName());
                return true;

            } else {

                return false;
            }

        } catch (Exception e) {
            LOG.severe(e.toString());
            // throw e;

        }
        return true;
    }

    @Override
    public boolean update(GymDetails gymDetails) throws Exception {

        String sql = "UPDATE gym_details SET Gym_Name=? , Gym_Location=?, Gym_Contact_Number=?  WHERE ID_Gym=?";

        PreparedStatement pstat = null;
        Connection conn = null;

        try {
            conn = dataSource.getConnection();
            conn.setAutoCommit(false);

            pstat = conn.prepareStatement(sql);

            pstat.setString(1, gymDetails.getGymName());
            pstat.setString(2, gymDetails.getGymLocation());
            pstat.setInt(3, gymDetails.getGymContactNumber());
            pstat.setInt(4, gymDetails.getGymId());

            int modifications = pstat.executeUpdate();

            conn.commit();

            if (modifications > 0) {

                //throw new SQLException("No changes were made UPDATE to gym_details=" + gymDetails.getGymName());
                return true;

            } else {

                return false;

            }

        } catch (Exception e) {
            LOG.severe(e.toString());
            conn.rollback();
            throw e;
        } finally {
            if (pstat != null) {

                pstat.close();

            }

            conn.setAutoCommit(true);

            if (conn != null) {

                conn.close();

            }
            return false;
        }

    }

    @Override
    public boolean delete(GymDetails gymDetails) throws Exception {

        String sql = "DELETE FROM gym_details WHERE ID_Gym=?";

        try {
            Connection conn = dataSource.getConnection();
            PreparedStatement pstat = conn.prepareStatement(sql);

            pstat.setInt(1, gymDetails.getGymId());

            int modifications = pstat.executeUpdate();
            if (modifications > 0) {
                //  throw new SQLException("No changes were made DELETE to ID_Gym=" + gymDetails.getGymId());

            }

        } catch (Exception e) {
            LOG.severe(e.toString());
            throw e;

        }
        return true;
    }

    @Override
    public GymDetails findByIdGym(int idGym) throws Exception {

        GymDetails gymDetails = null;

        String sql = "SELECT * FROM gym_details WHERE ID_Gym=?";

        ResultSet rs = null;

        try {
            Connection conn = dataSource.getConnection();
            PreparedStatement pstat = conn.prepareStatement(sql);

            pstat.setInt(1, idGym);

            rs = pstat.executeQuery();

            if (rs.next()) {
                int gymId = rs.getInt(1);
                String gymName = rs.getString(2);
                String gymLocation = rs.getString(3);
                int gymContactNumber = rs.getInt(4);

                gymDetails = new GymDetails(gymId, gymName, gymLocation, gymContactNumber);

            }
        } catch (Exception e) {
            LOG.severe(e.toString());
            // throw new SQLException(e.getMessage());

        } finally {
            if (rs != null) {
                rs.close();

            }

        }
        return gymDetails;

    }

    @Override
    public List<GymDetails> findByGymLocation(String location) throws Exception {
        List<GymDetails> list = new ArrayList<>();

        GymDetails gymDetails = null;
        String sql = " SELECT  * FROM gym_details WHERE Gym_Location=?";

        ResultSet rs = null;

        try {
            Connection conn = dataSource.getConnection();
            PreparedStatement pstat = conn.prepareStatement(sql);

            pstat.setString(1, location );
            //  '%' + username + '%'

            rs = pstat.executeQuery();

            while (rs.next()) {
                int gymId = rs.getInt(1);
                String gymName = rs.getString(2);
                String gymLocation = rs.getString(3);
                int gymContactNumber = rs.getInt(4);

                gymDetails = new GymDetails(gymId, gymName, gymLocation, gymContactNumber);
                list.add(gymDetails);

            }

        } catch (Exception e) {
            LOG.severe(e.toString());
            //throw new SQLException(e.getMessage());

        } finally {
            if (rs != null) {
                rs.close();

            }

        }
        return list;

    }

    @Override
    public List<GymDetails> findAll() throws Exception {

        List<GymDetails> list = new ArrayList<>();

        String sql = "SELECT * FROM gym_details";

        try (
                Connection conn = dataSource.getConnection();
                Statement stat = conn.createStatement();
                ResultSet rs = stat.executeQuery(sql);) {
            while (rs.next()) {

                int gymId = rs.getInt(1);
                String gymName = rs.getString(2);
                String gymLocation = rs.getString(3);
                int gymContactNumber = rs.getInt(4);

                GymDetails gymDetails = new GymDetails(gymId, gymName, gymLocation, gymContactNumber);
                list.add(gymDetails);

            }
        } catch (SQLException ex) {
            LOG.severe(ex.toString());
            // throw new SQLException(ex.getMessage());

        }
        return list;

    }

}
