package com.habravanEnterprise.fitnessForLife.dao.impl;

import com.habravanEnterprise.fitnessForLife.dao.ClientGymDetailsDaoIntf;
import com.habravanEnterprise.fitnessForLife.db.SingletonJDBCDataSource;
import com.habravanEnterprise.fitnessForLife.models.ClientGymDetails;
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
public class ClientGymDetailsDaoImpl implements ClientGymDetailsDaoIntf {

    private static final Logger LOG = Logger.getLogger(ClientDaoImpl.class.getName());
    private SingletonJDBCDataSource dataSource = SingletonJDBCDataSource.getInstance();

    @Override
    public boolean save(ClientGymDetails clientGym) throws Exception {

        String sql = "INSERT INTO client_gym_details VALUES (? ,? )";
        //                                   Pozitia          1  2
        try {
            Connection conn = dataSource.getConnection();
            PreparedStatement pstat = conn.prepareStatement(sql);

            pstat.setInt(1, clientGym.getClientID());
            pstat.setInt(2, clientGym.getGymID());

            int modifications = pstat.executeUpdate();
            if (modifications > 0) {
                // throw new SQLException("No changes were made SAVE to client_gym_details=" + clientGym.getClientID());
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
    public boolean update(ClientGymDetails clientGym) throws Exception {

        String sql = "UPDATE client_gym_details SET  Gym_ID=?  WHERE Client_ID=?";
        //                        Pozitia              1             2

        PreparedStatement pstat = null;
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            conn.setAutoCommit(false);

            pstat = conn.prepareStatement(sql);

            pstat.setInt(1, clientGym.getGymID());
            pstat.setInt(2, clientGym.getClientID());

            int modifications = pstat.executeUpdate();
            if (modifications > 0) {

                // throw new SQLException("No changes were made UPDATE to  client_gym_details=" + clientGym.getClientID());
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

        }

    }

    @Override
    public boolean delete(ClientGymDetails clientGym) throws Exception {

        String sql = "DELETE FROM client_gym_details WHERE Client_ID=?";

        try {
            Connection conn = dataSource.getConnection();
            PreparedStatement pstat = conn.prepareStatement(sql);

            pstat.setInt(1, clientGym.getClientID());

            int modifications = pstat.executeUpdate();
            if (modifications > 0) {
                //throw new SQLException("No changes were made DELETE to   client_gym_details=" + clientGym.getClientID());
                return true;

            } else {

                return false;

            }

        } catch (Exception e) {
            LOG.severe(e.toString());
            //throw e;

        }
     return true;
    }

    @Override
    public List< ClientGymDetails>  findByClientId(int clientID) throws Exception {
                List<ClientGymDetails> list = new ArrayList<>();

        ClientGymDetails clientGym = null;

        String sql = "SELECT * FROM client_gym_details WHERE Client_ID=?";

        ResultSet rs = null;

        try {
            Connection conn = dataSource.getConnection();
            PreparedStatement pstat = conn.prepareStatement(sql);

            pstat.setInt(1, clientID);

            rs = pstat.executeQuery();

            if (rs.next()) {
                int clientId = rs.getInt(1);
                int gymID = rs.getInt(2);

                clientGym = new ClientGymDetails(clientID, gymID);
                list.add(clientGym);

            

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
    public  List<ClientGymDetails> findByGymId(int gymID) throws Exception {
        
        List<ClientGymDetails> list = new ArrayList<>();

        ClientGymDetails clientGym = null;

        String sql = "SELECT * FROM client_gym_details WHERE Gym_ID=?";

        ResultSet rs = null;

        try {
            Connection conn = dataSource.getConnection();
            PreparedStatement pstat = conn.prepareStatement(sql);

            pstat.setInt(1, gymID);

            rs = pstat.executeQuery();

            if (rs.next()) {
                int clientId = rs.getInt(1);
                int gymId = rs.getInt(2);

                clientGym = new ClientGymDetails(gymID, clientId);
                list.add(clientGym);

           

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
    public List<ClientGymDetails> findAll() throws Exception {

        List<ClientGymDetails> list = new ArrayList<>();

        String sql = "SELECT * FROM client_gym_details";

        try (
                Connection conn = dataSource.getConnection();
                Statement stat = conn.createStatement();
                ResultSet rs = stat.executeQuery(sql);) {
            while (rs.next()) {

                int clientID = rs.getInt(1);
                int gymID = rs.getInt(2);

                ClientGymDetails clientGym = new ClientGymDetails(clientID, gymID);
                list.add(clientGym);

            }
        } catch (SQLException ex) {
            LOG.severe(ex.toString());
            // throw new SQLException(ex.getMessage());
           
        }

        return list;

    }

}
