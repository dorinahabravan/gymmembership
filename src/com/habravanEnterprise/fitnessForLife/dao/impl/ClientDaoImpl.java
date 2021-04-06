package com.habravanEnterprise.fitnessForLife.dao.impl;

import com.habravanEnterprise.fitnessForLife.dao.ClientDaoIntf;
import com.habravanEnterprise.fitnessForLife.db.SingletonJDBCDataSource;
import com.habravanEnterprise.fitnessForLife.models.Client;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author Dorina
 */
public class ClientDaoImpl implements ClientDaoIntf {

    private static final Logger LOG = Logger.getLogger(ClientDaoImpl.class.getName());

    private SingletonJDBCDataSource dataSource = SingletonJDBCDataSource.getInstance();

    @Override
    public boolean save(Client client) throws SQLException {
        String sql = "INSERT INTO clients VALUES (? ,? ,? ,?, ?, ?, ?)";
        //                     Pozitia            1  2  3  4  5  6  7
        try {
            Connection conn = dataSource.getConnection();
            PreparedStatement pstat = conn.prepareStatement(sql);

            pstat.setInt(1, client.getCustomerid());
            pstat.setInt(2, client.getUserID());
            pstat.setString(3, client.getFullname());
            pstat.setString(4, client.getLocation());
            pstat.setInt(5, client.getPhone_number());
            pstat.setString(6, client.getEmail_address());
            pstat.setDate(7, java.sql.Date.valueOf(client.getDate_of_birth()));

            int modifications = pstat.executeUpdate();
            if (modifications > 0) {
                return true;
                //throw new SQLException("No changes were made SAVE to clients=" + client.getFullname());
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
    public boolean update(Client client) throws SQLException {
        String sql = "UPDATE clients SET  User_ID=? ,FullName=? , Location=? , Phone_Number=? , Email_Address=?, Date_of_Birth=?  WHERE ID=?";
        //                                 1         2              3           4                  5                 6                     7
        PreparedStatement pstat = null;
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            //  conn.setAutoCommit(false);

            pstat = conn.prepareStatement(sql);

            pstat.setInt(1, client.getUserID());
            pstat.setString(2, client.getFullname());
            pstat.setString(3, client.getLocation());
            pstat.setInt(4, client.getPhone_number());
            pstat.setString(5, client.getEmail_address());
            pstat.setDate(6, java.sql.Date.valueOf(client.getDate_of_birth()));

            pstat.setInt(7, client.getCustomerid());

            int modifications = pstat.executeUpdate();
            //     conn.commit();
            if (modifications > 0) {

                return true;
                //throw new SQLException("No changes were made UPDATE to clients=" + client.getFullname());
            } else {

                return false;

            }

        } catch (Exception e) {
            LOG.severe(e.toString());
            if (conn != null) {
                //conn.rollback();
                throw e;
            }
        } finally {

            if (pstat != null) {

                pstat.close();

            }

            if (conn != null) {

                // conn.setAutoCommit(true);
                conn.close();

            }

        }
        return false;

    }

    @Override
    public boolean delete(Client client) throws Exception {

        String sql = "DELETE FROM clients WHERE ID=?";

        try {
            Connection conn = dataSource.getConnection();
            PreparedStatement pstat = conn.prepareStatement(sql);

            pstat.setInt(1, client.getCustomerid());

            int modifications = pstat.executeUpdate();
            if (modifications == 0) {
                throw new SQLException("No changes were made DELETE to ID=" + client.getCustomerid());
            }

        } catch (Exception e) {
            LOG.severe(e.toString());
            throw e;

        }
        return true;

    }

    @Override
    public Client findById(int idClient) throws Exception {

        Client client = null;

        String sql = "SELECT * FROM clients WHERE ID=?";

        ResultSet rs = null;

        try {
            Connection conn = dataSource.getConnection();
            PreparedStatement pstat = conn.prepareStatement(sql);

            pstat.setInt(1, idClient);

            rs = pstat.executeQuery();

            if (rs.next()) {
                int customerid = rs.getInt(1);
                int userid = rs.getInt(2);
                String fullname = rs.getString(3);
                String location = rs.getString(4);
                int phone_number = rs.getInt(5);
                String email_address = rs.getString(6);
                LocalDate date_of_birth = rs.getDate(7).toLocalDate();

                client = new Client(customerid, userid, fullname, location, phone_number, email_address, date_of_birth);

           
            }
        } catch (Exception e) {
            LOG.severe(e.toString());
            //throw new SQLException(e.getMessage());

        } finally {
            if (rs != null) {
                rs.close();

            }

        }
        return client;

    }

    @Override
    public  Client findByUserId(int idUser) throws Exception {

        Client client = null;

        String sql = "SELECT * FROM clients WHERE User_ID=?";

        ResultSet rs = null;

        try {
            Connection conn = dataSource.getConnection();
            PreparedStatement pstat = conn.prepareStatement(sql);

            pstat.setInt(1, idUser);

            rs = pstat.executeQuery();

            if (rs.next()) {
                int customerid = rs.getInt(1);
                int userid = rs.getInt(2);
                String fullname = rs.getString(3);
                String location = rs.getString(4);
                int phone_number = rs.getInt(5);
                String email_address = rs.getString(6);
                LocalDate date_of_birth = rs.getDate(7).toLocalDate();

                client = new Client(customerid, userid, fullname, location, phone_number, email_address, date_of_birth);

            
            }
        } catch (Exception e) {
            LOG.severe(e.toString());
            //throw new SQLException(e.getMessage());
           
        } finally {
            if (rs != null) {
                rs.close();

            }

        }
        return client;

    }

    @Override
    public List<Client> findByFullname(String fullName) throws Exception {
         List<Client> list = new ArrayList<>();

        Client client = null;
        //             SELECT   * FROM gym_memberdb.clients WHERE FullName  LIKE 'Janni Versace';
        String sql = " SELECT  * FROM clients WHERE FullName=?";

        ResultSet rs = null;

        try {
            Connection conn = dataSource.getConnection();
            PreparedStatement pstat = conn.prepareStatement(sql);

            pstat.setString(1, fullName);

            rs = pstat.executeQuery();

            if (rs.next()) {
                int customerid = rs.getInt(1);
                int userid = rs.getInt(2);
                String fullname = rs.getString(3);
                String location = rs.getString(4);
                int phone_number = rs.getInt(5);
                String email_address = rs.getString(6);
                LocalDate date_of_birth = rs.getDate(7).toLocalDate();

                client = new Client(customerid, userid, fullName, location, phone_number, email_address, date_of_birth);
                list.add(client);
                System.out.println("Client gasit="+client.getFullname());

           
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
    public  List<Client> findAll() throws Exception {
        List<Client> list = new ArrayList<>();

        String sql = "SELECT * FROM clients";

        try (
                Connection conn = dataSource.getConnection();
                Statement stat = conn.createStatement();
                ResultSet rs = stat.executeQuery(sql);) {
            while (rs.next()) {

                int customerid = rs.getInt(1);
                int userid = rs.getInt(2);
                String fullname = rs.getString(3);
                String location = rs.getString(4);
                int phone_number = rs.getInt(5);
                String email_address = rs.getString(6);
                LocalDate date_of_birth = rs.getDate(7).toLocalDate();

                Client client = new Client(customerid, userid, fullname, location, phone_number, email_address, date_of_birth);
                list.add(client);

            }
        } catch (Exception ex) {
            LOG.severe(ex.toString());
           // throw new SQLException(ex.getMessage());
          

        }

        return list;

    }

}
