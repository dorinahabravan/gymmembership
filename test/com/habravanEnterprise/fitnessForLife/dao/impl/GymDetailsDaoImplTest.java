/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.habravanEnterprise.fitnessForLife.dao.impl;

import com.habravanEnterprise.fitnessForLife.dao.GymDetailsDaoIntf;
import com.habravanEnterprise.fitnessForLife.db.SingletonJDBCDataSource;
import com.habravanEnterprise.fitnessForLife.db.StaticPropertyClass;
import com.habravanEnterprise.fitnessForLife.models.GymDetails;
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
public class GymDetailsDaoImplTest {

    private static final Logger LOG = Logger.getLogger(ClientDaoImplTest.class.getName());

    private static SingletonJDBCDataSource instance = null;
    private Connection conn = null;
    private GymDetailsDaoIntf instanceDao = null;

    private static boolean DEBUG_temp = true;

    public GymDetailsDaoImplTest() {

        System.out.println("GymDetailsDaoImpTest");
        instance = SingletonJDBCDataSource.getInstance();
        instanceDao = new GymDetailsDaoImpl();

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

            String sqlCreate = "CREATE TABLE gym_details (\n"
                    + "  `ID_Gym` int(11) NOT NULL AUTO_INCREMENT,\n"
                    + "  `Gym_Name` varchar(155) NOT NULL,\n"
                    + "  `Gym_Location` varchar(155) DEFAULT NULL,\n"
                    + "  `Gym_Contact_Number` int(11) NOT NULL,\n"
                    + "  PRIMARY KEY (`ID_Gym`)\n"
                    + ") ENGINE=InnoDB DEFAULT CHARSET=utf8;";

            st.executeUpdate(sqlCreate);
            st.close();

            st = conn.createStatement();
            System.out.println("Created table");

            String sqlInsert = "INSERT INTO gym_details "
                    + "VALUES (1,'PureGym','Manchester',77883399),"
                    + "(2,'EnergyFitness','London',74996655),"
                    + "(3,'FitnessClass','Liverpool',71223366);";

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

            String sqlDrop = "DROP TABLE IF EXISTS gym_details ;";
            st.executeUpdate(sqlDrop);
            st.close();

            System.out.println("The table gym_details has been deleted");
        } catch (SQLException ex) {

            LOG.severe(ex.toString());
        }

    }

    /**
     * Test of save method, of class GymDetailsDaoImpl.
     */
    @Test
    public void testSave() throws Exception {
        System.out.println("save");
        GymDetails gymDetails = new GymDetails(4, "PureGym4", "Manchester4", 77883399);
        boolean expResult = true;
        boolean result = instanceDao.save(gymDetails);
        assertEquals(expResult, result);

    }

    /**
     * Test of update method, of class GymDetailsDaoImpl.
     */
    @Test
    public void testUpdate() throws Exception {
        System.out.println("update");
        GymDetails gymDetails = new GymDetails(1, "PureGymUpdated", "ManchesterUpdated", 77883399);
        boolean expresult = true;
        boolean result = instanceDao.update(gymDetails);

    }

    /**
     * Test of delete method, of class GymDetailsDaoImpl.
     */
    @Test
    public void testDelete() throws Exception {
        System.out.println("delete");
        GymDetails gymDetails = new GymDetails(1, "PureGym", "Manchester", 77883399);
        boolean expResult = true;
        boolean result = instanceDao.delete(gymDetails);

    }

    /**
     * Test of findByIdGym method, of class GymDetailsDaoImpl.
     */
    @Test
    public void testFindByIdGym() throws Exception {
        System.out.println("findByIdGym");
       

        GymDetails expResult = new GymDetails(1, "PureGym", "Manchester", 77883399);

        GymDetails resultNotExist = instanceDao.findByIdGym(150);
        assertNull(resultNotExist);

        GymDetails result = instanceDao.findByIdGym(1);
        assertNotEquals(expResult, null);
        assertEquals(expResult, result);
        assertEquals(expResult.getGymName(), result.getGymName());

    }

    /**
     * Test of findByGymLocation method, of class GymDetailsDaoImpl.
     */
    @Test
    public void testFindByGymLocation() throws Exception {
        System.out.println("findByGymLocation");
        //String location = "";
        // boolean expResult = true;
         int expResult =1;

        List<GymDetails> resultNotExist = instanceDao.findByGymLocation("Dog~####");
        assertTrue(resultNotExist.isEmpty());

        List<GymDetails> result = instanceDao.findByGymLocation("Manchester");
        assertEquals(expResult, result.size());

    }

    /**
     * Test of findAll method, of class GymDetailsDaoImpl.
     */
    @Test
    public void testFindAll() throws Exception {
        System.out.println("findAll");
        int expResult = 3;
        List<GymDetails> result = instanceDao.findAll();
        assertEquals(expResult, result.size());

    }

}
