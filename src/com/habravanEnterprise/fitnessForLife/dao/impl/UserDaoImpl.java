package com.habravanEnterprise.fitnessForLife.dao.impl;

import com.habravanEnterprise.fitnessForLife.dao.UserDaoIntf;
import com.habravanEnterprise.fitnessForLife.db.SingletonJDBCDataSource;
import com.habravanEnterprise.fitnessForLife.models.User;
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
public class UserDaoImpl implements UserDaoIntf {

    private static final Logger LOG = Logger.getLogger(UserDaoImpl.class.getName());
    private SingletonJDBCDataSource dataSource = SingletonJDBCDataSource.getInstance();

    @Override
    public boolean save(User user) throws SQLException {
        String sql = "INSERT INTO user VALUES (? ,? ,? ,?)";
        //                     Pozitia         1  2  3  4

        // Varianta cu Statement simplu.
        /* String sql=" INSERT INTO user VALUES("+user.getId()+", '"+user.getUsename()+"',
                           + "'"+user.getPassword()+"' , '"+user.getConnectionDate().toString()+"' );
         */
        try {
            // Aici este varianta cu Prepared Statement 
            Connection conn = dataSource.getConnection();
            PreparedStatement pstat = conn.prepareStatement(sql);

            pstat.setInt(1, user.getId());
            pstat.setString(2, user.getUsername());
            pstat.setString(3, user.getPassword());
            pstat.setDate(4, java.sql.Date.valueOf(user.getConnectionDate()));
            //  pstat.setDate( 4, java.sql.Date.valueOf(user.getConnectionDate()));

            int modifications = pstat.executeUpdate();
            if (modifications > 0) {
                return true;
                //throw new SQLException("No changes were made SAVE to user=" + user.getUsername());

            } else {

                return false;
            }

        } catch (Exception e) {
            LOG.severe(e.toString());
            //throw e;
            return true;
        }

    }

    @Override
    public boolean update(User user) throws SQLException {
        //                                     1            2                3          4
        String sql = "UPDATE user SET User_Name=? , User_Password=?, Connection_Date=? WHERE ID=?";

        PreparedStatement pstat = null;
        Connection conn = null;

        try {
            conn = dataSource.getConnection();
            // conn.setAutoCommit(false);

            pstat = conn.prepareStatement(sql);

            pstat.setString(1, user.getUsername());
            pstat.setString(2, user.getPassword());
            pstat.setDate(3, java.sql.Date.valueOf(user.getConnectionDate()));
            //  pstat.setDate(3, (Date) user.getConnectionDate());
            pstat.setInt(4, user.getId());

            int modifications = pstat.executeUpdate();

            //  conn.commit();
            if (modifications > 0) {
                return true;
                //throw new SQLException("No changes were made SAVE to user=" + user.getUsername());

            } else {

                return false;
            }

            // if (modifications == 0) {
            //   throw new SQLException("No changes were made UPDATE to user=" + user.getUsername());
            //  }
        } catch (Exception e) {
            LOG.severe(e.toString());
            //  conn.rollback();

            throw e;
        } finally {
            if (pstat != null) {

                pstat.close();

            }

            // conn.setAutoCommit(true);
            if (conn != null) {

                conn.close();

            }

        }

    }

    @Override
    public boolean delete(User user) throws Exception {

        String sql = "DELETE FROM user WHERE ID=?";

        try {
            Connection conn = dataSource.getConnection();
            PreparedStatement pstat = conn.prepareStatement(sql);

            pstat.setInt(1, user.getId());

            int modifications = pstat.executeUpdate();
            if (modifications > 0) {
                return true;
                // throw new SQLException("No changes were made DELETE to ID=" + user.getId());
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
    public User findById(int idUser) throws SQLException {
        User user = null;

        String sql = "SELECT * FROM user WHERE ID=?";

        ResultSet rs = null;

        try {
            Connection conn = dataSource.getConnection();
            PreparedStatement pstat = conn.prepareStatement(sql);

            pstat.setInt(1, idUser);

            rs = pstat.executeQuery();

            if (rs.next()) {
                int id = rs.getInt(1);
                String username = rs.getString(2);
                String password = rs.getString(3);

                LocalDate date = rs.getDate(4).toLocalDate();

                user = new User(id, username, password, date);

           

            }
        } catch (Exception e) {
            LOG.severe(e.toString());
//            throw new SQLException(e.getMessage());
        } finally {
            if (rs != null) {
                rs.close();

            }

        }
        return user;

    }

    @Override
    public List<User> findByUsername(String username) throws SQLException {
         List<User> list = new ArrayList<>();

        User user = null;
        String sql = " SELECT * FROM user WHERE User_Name LIKE ?";

        ResultSet rs = null;

        try {
            Connection conn = dataSource.getConnection();
            PreparedStatement pstat = conn.prepareStatement(sql);

            pstat.setString(1, '%' + username + '%');

            rs = pstat.executeQuery();

            while (rs.next()) {
                int id = rs.getInt(1);
                username = rs.getString(2);
                String password = rs.getString(3);

                LocalDate date = rs.getDate(4).toLocalDate();

                user = new User(id, username, password, date);
                list.add(user);
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
    public List<User> findAll() throws SQLException {
        List<User> list = new ArrayList<>();

        String sql = "SELECT * FROM user";

        try (
                Connection conn = dataSource.getConnection();
                Statement stat = conn.createStatement();
                ResultSet rs = stat.executeQuery(sql);) {
            while (rs.next()) {

                int id = rs.getInt(1);
                String username = rs.getString(2);
                String password = rs.getString(3);
                LocalDate date = rs.getDate(4).toLocalDate();

                User user = new User(id, username, password, date);
                list.add(user);

            }
          

        } catch (SQLException ex) {
            LOG.severe(ex.toString());
            //throw new SQLException(ex.getMessage());

           
        }
      return list;
    }

}
