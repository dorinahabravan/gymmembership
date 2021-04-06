package com.habravanEnterprise.fitnessForLife.db;

import static com.habravanEnterprise.fitnessForLife.db.StaticPropertyClass.*;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;

/**
 *
 * @author Dorina
 */
public  class SingletonJDBCDataSource {

    private static final Logger LOG = Logger.getLogger(SingletonJDBCDataSource.class.getName());

    // Componentele configurarii
    private String dbUsername;
    private String dbPassword;
    private String dbUrl;
    private String dbName;

    private String dbDriverName;
    private Connection connection;

    //public Connection connection;
    //public Connection getConnection;
    private SingletonJDBCDataSource() {
        
        //if(!DEBUG){

        loadProperties();
        //LOG.info("successfully loaded BD connection properties");
        loadDriver();
        // LOG.info("driver loaded successfully");
        openConnection();
        //LOG.info("successfully connected to the database");
        
        
       // }
    }

    public static SingletonJDBCDataSource getInstance() {
        return SingletonJDBCDataSourceHolder.INSTANCE;

    }

    public boolean loadProperties() {
        try {

            Properties props = new Properties();
            
            String fileProperties=null;
            if (!DEBUG) {
                fileProperties= JDBC_FILE;
            } else {
                 fileProperties= JDBC_TEST_FILE;
                System.out.println("A fost ales test file");
                
            }
            props.load(new FileReader(fileProperties));

            dbUsername = props.getProperty("dbUsername");
            if (dbUsername == null) {
                throw new IOException("dbUsername is null");
            }

            dbPassword = props.getProperty("dbPassword");
            if (dbPassword == null) {
                throw new IOException("dbPassword is null");
            }

            dbName = props.getProperty("dbName");
            if (dbName == null) {
                throw new IOException("dbName is null");
            }

            dbDriverName = props.getProperty("dbDriverName");
            if (dbDriverName == null) {
                throw new IOException("dbDrivername is null");
            }

            dbUrl = props.getProperty("dbUrl") + dbName;
            if (dbUrl == null) {
                throw new IOException("dbUrl is  null");
            }

            return true;
        } catch (IOException ioe) {
            LOG.severe(ioe.toString());

        }
        return false;

    }

    /**
     *
     * @return
     * @throws SQLException
     */
    public Connection getConnection() throws SQLException {

        if (openConnection()) {
            return connection;

        } else {
            throw new SQLException("error open connection");
            
        }
    }

    public boolean openConnection() {
        try {
            if (connection == null || (connection !=null && connection.isClosed())) {
                connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
            }
           return true;
        } catch (SQLException e) {
            LOG.severe(e.toString());
            return false;
        }

    }

    // Intrebarea este daca metoda asta trebuie aici sau in la testare?
    //private void testConnection() throws SQLException{
    // if (getConnection() == null || (connection != null && connection.isClosed())) {
    // /throw new SQLException("error test connection");
    //  }
    // }
    public boolean loadDriver() {
        try {
            Class.forName(dbDriverName).newInstance();

            return true;
        } catch (Exception e) {
            LOG.severe(e.toString());
            return false;

        }
    }

    private static class SingletonJDBCDataSourceHolder {

        public static final SingletonJDBCDataSource INSTANCE = new SingletonJDBCDataSource();
    }
}
