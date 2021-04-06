package com.habravanEnterprise.fitnessForLife.dao.impl;

import com.habravanEnterprise.fitnessForLife.dao.UserDaoIntf;
import com.habravanEnterprise.fitnessForLife.db.SingletonJDBCDataSource;
import com.habravanEnterprise.fitnessForLife.db.StaticPropertyClass;
import com.habravanEnterprise.fitnessForLife.models.User;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Month;
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
public class UserDaoImplTest {

    private static final Logger LOG = Logger.getLogger(UserDaoImplTest.class.getName());

    private static SingletonJDBCDataSource instance = null;
    private Connection conn = null;
    private UserDaoIntf instanceDao = null;

    private static boolean DEBUG_temp = true;

    public UserDaoImplTest() {
        System.out.println(" UserDaoImplTest()");
        instance = SingletonJDBCDataSource.getInstance();
        instanceDao = new UserDaoImpl();
    }

    @BeforeClass
    public static void setUpClass() {
        System.out.println("setUpClass()");

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
            // Comanda USE db creata
            //Cream tabele (db-data-schema)
            //Insert date din(db-data-schema)

            conn = instance.getConnection();
            System.out.println("Connection established...");
            Statement st = conn.createStatement();

            String sqlCreate = "CREATE TABLE user (\n"
                    + "  `ID` int(11) NOT NULL,\n"
                    + "  `User_Name` varchar(155) DEFAULT NULL,\n"
                    + "  `User_Password` varchar(155) DEFAULT NULL,\n"
                    + "  `Connection_Date` date DEFAULT NULL,\n"
                    + "  KEY `ID_idx` (`ID`)\n"
                    //  + "  CONSTRAINT `ID` FOREIGN KEY (`ID`) REFERENCES `clients` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION\n"
                    + ") ENGINE=InnoDB DEFAULT CHARSET=utf8;\n"
                    + "";

            st.executeUpdate(sqlCreate);
            st.close();

            st = conn.createStatement();
            System.out.println("Created table user");

            String sqlInsert = "INSERT INTO user "
                    + "VALUES "
                    + "(1,'Tom','1234','2010-02-20');\n";

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
            //Stergerea tabelei existente
            conn = instance.getConnection();
            System.out.println("tearDown() ");

            Statement st = conn.createStatement();

            String sqlDrop = "DROP TABLE IF EXISTS user;";
            st.executeUpdate(sqlDrop);
            st.close();

            System.out.println("The table user has been deleted");
        } catch (SQLException ex) {
            LOG.severe(ex.toString());

        }

    }

    /**
     * Test of save method, of class UserDaoImpl.
     */
    @Test
    public void testSave() throws Exception {
        System.out.println("save");
        User user = new User(2, "TomSave", "1234", LocalDate.of(2010, Month.FEBRUARY, 20));

        boolean expResult = true;
        boolean result = instanceDao.save(user);
        assertEquals("The save test did  passed successfully", expResult, result);

    }

    /**
     * Test of update method, of class UserDaoImpl.
     */
    @Test
    public void testUpdate() {
        try {
            System.out.println("update");
            User user = new User(1, "TomUpdated", "14566", LocalDate.of(2010, Month.FEBRUARY, 10));
            boolean expResult = true;
            boolean result = instanceDao.update(user);
            assertEquals(expResult, result);
        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    /**
     * Test of delete method, of class UserDaoImpl.
     */
    @Test
    public void testDelete() throws Exception {
        System.out.println("delete");
        User user = new User(1, "Tom", "1234", LocalDate.of(2010, Month.FEBRUARY, 20));
        boolean expResult = true;
        boolean result = instanceDao.delete(user);
        assertEquals(expResult, result);

    }

    /**
     * Test of findById method, of class UserDaoImpl.
     */
    @Test
    public void testFindById() throws Exception {
        System.out.println("findById");
        User expResult = new User(1, "Tom", "1234", LocalDate.of(2010, Month.FEBRUARY, 20));

        User resultNotExist = instanceDao.findById(100);
        assertNull(resultNotExist);

        User result = instanceDao.findById(1);
        assertNotEquals(expResult, null);
        assertEquals(expResult, result);
        assertEquals(expResult.getUsername(), result.getUsername());

    }

    /**
     * Test of findByUsername method, of class UserDaoImpl.
     */
    @Test
    public void testFindByUsername() throws Exception {
        System.out.println("findByUsername");
        boolean expResult = true;

        List<User> resultNotExist = instanceDao.findByUsername("Does?#");
        assertTrue(resultNotExist.size() == 0);

        List<User> result = instanceDao.findByUsername("Tom");
        assertTrue(result.size() > 0);

    }

    /**
     * Test of findAll method, of class UserDaoImpl.
     */
    @Test
    public void testFindAll() throws Exception {
        System.out.println("findAll");
        int expResult = 1;
        List<User> result = instanceDao.findAll();
        assertEquals(expResult, result.size());

    }

}
