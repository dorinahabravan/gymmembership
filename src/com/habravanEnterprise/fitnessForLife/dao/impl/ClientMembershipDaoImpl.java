package com.habravanEnterprise.fitnessForLife.dao.impl;

import com.habravanEnterprise.fitnessForLife.dao.ClientMembershipDaoIntf;
import com.habravanEnterprise.fitnessForLife.db.SingletonJDBCDataSource;
import com.habravanEnterprise.fitnessForLife.models.ClientMembership;
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
public class ClientMembershipDaoImpl implements ClientMembershipDaoIntf {

    private static final Logger LOG = Logger.getLogger(ClientMembershipDaoImpl.class.getName());
    private SingletonJDBCDataSource dataSource = SingletonJDBCDataSource.getInstance();

    @Override
    public boolean save(ClientMembership clientMemb) throws Exception {

        String sql = "INSERT INTO client_membership VALUES (? ,? )";
        //                                   Pozitia        1  2
        try {
            Connection conn = dataSource.getConnection();
            PreparedStatement pstat = conn.prepareStatement(sql);

            pstat.setInt(1, clientMemb.getClientId());
            pstat.setInt(2, clientMemb.getGymId());

            int modifications = pstat.executeUpdate();
            if (modifications > 0) {
                // throw new SQLException("No changes were made SAVE to client_membership=" + clientMemb.getClientId());

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
    public boolean update(ClientMembership clientMemb) throws Exception {

        String sql = "UPDATE client_membership SET  Membership_ID=?  WHERE Client_ID=?";
        //                        Pozitia                    1                     2

        PreparedStatement pstat = null;
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            conn.setAutoCommit(false);

            pstat = conn.prepareStatement(sql);

            pstat.setInt(1, clientMemb.getGymId());
            pstat.setInt(2, clientMemb.getClientId());

            int modifications = pstat.executeUpdate();
            if (modifications > 0) {

                //throw new SQLException("No changes were made UPDATE to  client_membership=" + clientMemb.getClientId());
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
    public boolean delete(ClientMembership clientMemb) throws Exception {

        String sql = "DELETE FROM client_membership WHERE Client_ID=?";

        try {
            Connection conn = dataSource.getConnection();
            PreparedStatement pstat = conn.prepareStatement(sql);

            pstat.setInt(1, clientMemb.getClientId());

            int modifications = pstat.executeUpdate();
            if (modifications > 0) {
                // throw new SQLException("No changes were made DELETE to   client_membership=" + clientMemb.getClientId());
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
    public ClientMembership findByClientId(int clientID) throws Exception {

        ClientMembership clientMemb = null;

        String sql = "SELECT * FROM client_membership WHERE Client_ID=?";

        ResultSet rs = null;

        try {
            Connection conn = dataSource.getConnection();
            PreparedStatement pstat = conn.prepareStatement(sql);

            pstat.setInt(1, clientID);

            rs = pstat.executeQuery();

            if (rs.next()) {
                int clientId = rs.getInt(1);
                int gymId = rs.getInt(2);

                ClientMembership clientMember = new ClientMembership(clientId, gymId);

            }
        } catch (Exception e) {
            LOG.severe(e.toString());
            //throw new SQLException(e.getMessage());
        } finally {
            if (rs != null) {
                rs.close();

            }

        }
        return clientMemb;

    }

    @Override
    public List <ClientMembership> findByMembershipId(int gymID) throws Exception {
        List<ClientMembership> list = new ArrayList<>();


        ClientMembership clientMemb = null;

        String sql = "SELECT * FROM client_membership WHERE Membership_ID=?";

        ResultSet rs = null;

        try {
            Connection conn = dataSource.getConnection();
            PreparedStatement pstat = conn.prepareStatement(sql);

            pstat.setInt(1, gymID);

            rs = pstat.executeQuery();

            if (rs.next()) {
                int gymId = rs.getInt(1);
                int clientId = rs.getInt(2);

                ClientMembership clientMember = new ClientMembership(gymId, clientId);
                      list.add(clientMemb);

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
    public List<ClientMembership> findAll() throws Exception {
        List<ClientMembership> list = new ArrayList<>();

        String sql = "SELECT * FROM client_membership";

        try (
                Connection conn = dataSource.getConnection();
                Statement stat = conn.createStatement();
                ResultSet rs = stat.executeQuery(sql);) {
            while (rs.next()) {

                int clientID = rs.getInt(1);
                int gymID = rs.getInt(2);

                ClientMembership clientMemb = new ClientMembership(clientID, gymID);
                list.add(clientMemb);

            }
        } catch (SQLException ex) {
            LOG.severe(ex.toString());
            //throw new SQLException(ex.getMessage());

        }

        return list;

    }

}
