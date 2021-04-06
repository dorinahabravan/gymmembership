package com.habravanEnterprise.fitnessForLife.dao.impl;

import com.habravanEnterprise.fitnessForLife.dao.ClientGymDetailsDaoIntf;
import com.habravanEnterprise.fitnessForLife.db.SingletonJDBCDataSource;
import com.habravanEnterprise.fitnessForLife.db.StaticPropertyClass;
import com.habravanEnterprise.fitnessForLife.models.ClientGymDetails;
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
public class ClientGymDetailsDaoImplTest {

    private static final Logger LOG = Logger.getLogger(ClientDaoImplTest.class.getName());

    private static SingletonJDBCDataSource instance = null;
    private Connection conn = null;
    private ClientGymDetailsDaoIntf instanceDao = null;
    private static boolean DEBUG_temp = true;

    public ClientGymDetailsDaoImplTest() {
        System.out.println("ClientGymDetailsDaoImplTest");
        instance = SingletonJDBCDataSource.getInstance();
        instanceDao = new ClientGymDetailsDaoImpl();

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

            String sqlCreate = "CREATE TABLE client_gym_details (\n"
                    + "  `Client_ID` int(11) NOT NULL,\n"
                    + "  `Gym_ID` int(11) NOT NULL,\n"
                    + "  KEY `Client_ID_idx` (`Client_ID`),\n"
                    + "  KEY `Gym_ID_idx` (`Gym_ID`)\n"
                    //+ "  CONSTRAINT `Client_Nr` FOREIGN KEY (`Client_ID`) REFERENCES `clients` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,\n"
                   // + "  CONSTRAINT `Gym_ID` FOREIGN KEY (`Gym_ID`) REFERENCES `gym_details` (`ID_Gym`) ON DELETE NO ACTION ON UPDATE NO ACTION\n"
                    + ") ENGINE=InnoDB DEFAULT CHARSET=utf8;";

            st.executeUpdate(sqlCreate);
            st.close();

            st = conn.createStatement();
            System.out.println("Created table");

            String sqlInsert = "INSERT INTO client_gym_details"
                    + " VALUES "
                    + "(1,1),"
                    + "(2,2),"
                    + "(3,3);";

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

            String sqlDrop = "DROP TABLE IF EXISTS client_gym_details ;";
            st.executeUpdate(sqlDrop);
            st.close();

            System.out.println("The table client_gym_details  has been deleted");
        } catch (SQLException ex) {
            LOG.severe(ex.toString());

        }

    }

    /**
     * Test of save method, of class ClientGymDetailsDaoImpl.
     */
    @Test
    public void testSave() throws Exception {
        System.out.println("save");
        ClientGymDetails clientGym = new ClientGymDetails(1, 1);
        boolean expResult = true;
        boolean result = instanceDao.save(clientGym);
        assertEquals(expResult, result);

    }

    /**
     * Test of update method, of class ClientGymDetailsDaoImpl.
     */
    @Test
    public void testUpdate() throws Exception {
        try {
            System.out.println("update");
            ClientGymDetails clientGym = new ClientGymDetails(1, 2);
            boolean expResult = true;
            boolean result = instanceDao.update(clientGym);
            assertEquals(expResult, result);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    /**
     * Test of delete method, of class ClientGymDetailsDaoImpl.
     */
    @Test
    public void testDelete() throws Exception {
        System.out.println("delete");
        ClientGymDetails clientGym = new ClientGymDetails(1, 1);
        boolean expResult = true;
        boolean result = instanceDao.delete(clientGym);
        assertEquals(expResult, result);

    }

    /**
     * Test of findByClientId method, of class ClientGymDetailsDaoImpl.
     */
    @Test
    public void testFindByClientId() throws Exception {
        System.out.println("findByClientId");
        int exprRezult = 1;

        List<ClientGymDetails> resultNotExist = instanceDao.findByClientId(100);
        assertTrue(resultNotExist.size() == 0);

        List<ClientGymDetails> result = instanceDao.findByClientId(1);

    }

    /**
     * Test of findByGymId method, of class ClientGymDetailsDaoImpl.
     */
    @Test
    public void testFindByGymId() throws Exception {
        System.out.println("findByGymId");
        int expResult = 3;

        List<ClientGymDetails> resultNotExist = instanceDao.findByGymId(100);
        assertTrue(resultNotExist.size() == 0);

        List<ClientGymDetails> result = instanceDao.findByGymId(3);

    }

    /**
     * Test of findAll method, of class ClientGymDetailsDaoImpl.
     */
    @Test
    public void testFindAll() throws Exception {
        System.out.println("findAll");
        int expResult = 3;
        List<ClientGymDetails> result = instanceDao.findAll();

    }

}
