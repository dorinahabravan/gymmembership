/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.habravanEnterprise.fitnessForLife.dao.impl;

import com.habravanEnterprise.fitnessForLife.dao.ClientMembershipDaoIntf;
import com.habravanEnterprise.fitnessForLife.db.SingletonJDBCDataSource;
import com.habravanEnterprise.fitnessForLife.db.StaticPropertyClass;
import com.habravanEnterprise.fitnessForLife.models.ClientMembership;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Dorina
 */
public class ClientMembershipDaoImplTest {

    private static final Logger LOG = Logger.getLogger(ClientDaoImplTest.class.getName());

    private static SingletonJDBCDataSource instance = null;
    private Connection conn = null;
    private ClientMembershipDaoIntf instanceDao = null;

    private static boolean DEBUG_temp = true;

    public ClientMembershipDaoImplTest() {
        System.out.println("ClientMembershipDaoImpTest");
        instance = SingletonJDBCDataSource.getInstance();
        instanceDao = new ClientMembershipDaoImpl();

    }

    @BeforeClass
    public static void setUpClass() {

        System.out.println("setUpClass");

        DEBUG_temp = StaticPropertyClass.DEBUG;

        StaticPropertyClass.DEBUG = true;

    }

    @AfterClass
    public static void tearDownClass() {
        StaticPropertyClass.DEBUG = DEBUG_temp;

    }

    @Before
    public void setUp() {

        try {
            conn = instance.getConnection();
            System.out.println("Connection established.....");
            Statement st = conn.createStatement();

            String sqlCreate = "CREATE TABLE client_membership (\n"
                    + "  `Client_ID` int(11) NOT NULL,\n"
                    + "  `Membership_ID` int(11) NOT NULL,\n"
                    + "  KEY `Client_ID_idx` (`Client_ID`),\n"
                    + "  KEY `Membership_ID_idx` (`Membership_ID`)\n"
                   // + "  CONSTRAINT `Client_ID` FOREIGN KEY (`Client_ID`) REFERENCES `clients` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,\n"
                   // + "  CONSTRAINT `Membership_ID` FOREIGN KEY (`Membership_ID`) REFERENCES `membership` (`ID_Membership`) ON DELETE NO ACTION ON UPDATE NO ACTION\n"
                    + ") ENGINE=InnoDB DEFAULT CHARSET=utf8;";

            st.executeUpdate(sqlCreate);
            st.close();

            st = conn.createStatement();
            System.out.println("Created table");

            String sqlInsert = " INSERT INTO client_membership"
                    + " VALUES (1,1),"
                    + "(2,2);";
            st.executeUpdate(sqlInsert);
            st.close();

            System.out.println("The data inserted in the table");

        } catch (SQLException ex) {
            LOG.severe(ex.toString());
        }

    }

    @After
    public void tearDown() {

        try {
            conn = instance.getConnection();
            System.out.println("tearDown() ");

            Statement st = conn.createStatement();

            String sqlDrop = "DROP TABLE IF EXISTS client_membership;";
            st.executeUpdate(sqlDrop);
            st.close();

            System.out.println("The table client_membership has been deleted");
        } catch (SQLException ex) {

            LOG.severe(ex.toString());
        }

    }

    /**
     * Test of save method, of class ClientMembershipDaoImpl.
     */
    @Test
    public void testSave() throws Exception {
        System.out.println("save");
        ClientMembership clientMemb = new ClientMembership(1, 1);
        boolean expResult = true;
        boolean result = instanceDao.save(clientMemb);
        assertEquals(expResult, result);

    }

    /**
     * Test of update method, of class ClientMembershipDaoImpl.
     */
    @Test
    public void testUpdate() throws Exception {
        try {
            System.out.println("update");
            ClientMembership clientMemb = new ClientMembership(2, 2);
            boolean expResult = true;
            boolean result = instanceDao.update(clientMemb);
            assertEquals(expResult, result);

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    /**
     * Test of delete method, of class ClientMembershipDaoImpl.
     */
    @Test
    public void testDelete() throws Exception {
        System.out.println("delete");
        ClientMembership clientMemb = new ClientMembership(1, 1);
        boolean expResult = true;
        boolean result = instanceDao.delete(clientMemb);
        assertEquals(expResult, result);

    }

    /**
     * Test of findByClientId method, of class ClientMembershipDaoImpl.
     */
    @Test
    public void testFindByClientId() throws Exception {
        System.out.println("findByClientId");

        ClientMembership expResult = new ClientMembership(2, 2);

        ClientMembership resultNotExist = instanceDao.findByClientId(150);
        assertNull(resultNotExist);

        ClientMembership result = instanceDao.findByClientId(2);
        assertNotEquals(expResult, null);
        assertNotEquals(expResult, result);
        assertNotEquals(expResult, result);

    }

    /**
     * Test of findByMembershipId method, of class ClientMembershipDaoImpl.
     */
    @Test
    public void testFindByMembershipId() throws Exception {
        System.out.println("findByMembershipId");
        int expResult = 2;

        List<ClientMembership> resultNotExist = instanceDao.findByMembershipId(150);
        assertTrue(resultNotExist.size() == 0);

        List<ClientMembership> result = instanceDao.findByMembershipId(2);

    }

    /**
     * Test of findAll method, of class ClientMembershipDaoImpl.
     */
    @Test
    public void testFindAll() throws Exception {
        System.out.println("findAll");
        int expResult = 2;
        List<ClientMembership> result = instanceDao.findAll();
    }

}
