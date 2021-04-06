package com.habravanEnterprise.fitnessForLife.dao.impl;

import com.habravanEnterprise.fitnessForLife.dao.ClientDaoIntf;
import com.habravanEnterprise.fitnessForLife.db.SingletonJDBCDataSource;
import com.habravanEnterprise.fitnessForLife.db.StaticPropertyClass;
import com.habravanEnterprise.fitnessForLife.models.Client;
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
public class ClientDaoImplTest {

    private static final Logger LOG = Logger.getLogger(ClientDaoImplTest.class.getName());

    private static SingletonJDBCDataSource instance = null;
    private Connection conn = null;
    private ClientDaoIntf instanceDao = null;
// True e in regim de testare
    private static boolean DEBUG_temp = true;

    //Directoriu fisierului sql(db-mysql-schema))
    //String dir = "D:\\GymMembership\\src\\com\\habravanEnterprise\\fitnessForLife\\db\\";
    public ClientDaoImplTest() {
        System.out.println("ClientDaoImpTest");
        instance = SingletonJDBCDataSource.getInstance();
        instanceDao = new ClientDaoImpl();

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
            // Baza de date gym_memberdb_test
            //Crearea tabelei clients
            //Introducerea datelor in tabela data.
            conn = instance.getConnection();
            System.out.println("Connection established.....");
            Statement st = conn.createStatement();

            String sqlCreate
                    = "CREATE TABLE clients (\n"
                    + "`ID` int(11) NOT NULL AUTO_INCREMENT,\n"
                    + "`User_ID` int(11) NOT NULL,\n"
                    + "`FullName` varchar(155) DEFAULT NULL,\n"
                    + "`Location` varchar(155) DEFAULT NULL,\n"
                    + " `Phone_Number` int(11) DEFAULT NULL,\n"
                    + " `Email_Address` varchar(155) DEFAULT NULL,\n"
                    + "`Date_of_Birth` varchar(155) DEFAULT NULL,\n"
                    + " PRIMARY KEY (`ID`),\n"
                    + "UNIQUE KEY `FullName_UNIQUE` (`FullName`)\n"
                    + ") ENGINE = InnoDB AUTO_INCREMENT = 5 DEFAULT CHARSET = utf8;\n"
                    + "";

            st.executeUpdate(sqlCreate);
            st.close();

            st = conn.createStatement();
            System.out.println("Created table");

            String sqlInsert = "INSERT INTO  clients "
                    + "VALUES "
                    + "(1,1,'Tom Hilfigher','Manchester',68754055,'tom_b@gmail.com','1986-02-02'),"
                    + "(2,2,'Laura Mercier','London',69883325,'laura@gmail.com','1999-02-11'),"
                    + "(3,3,'Giorgio Armani','Birmingham',69772255,'armanig@gmail.com','1989-10-10'),"
                    + "(4,4,'Janni Versace','Liverpool',67889922,'janniers@gmail.com','2000-09-07');\n";

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

            String sqlDrop = "DROP TABLE IF EXISTS `clients`;";
            st.executeUpdate(sqlDrop);
            st.close();

            System.out.println("The table clients has been deleted");

        } catch (SQLException ex) {
            LOG.severe(ex.toString());

        }

    }

    /**
     * Test of save method, of class ClientDaoImpl.
     */
    @Test
    public void testSave() throws Exception {
        System.out.println("save");
        //Client client = new Client(1, 1, "Tom Hilfigher", "Manchester", 68754055, "tom_b@gmail.com", LocalDate.of(1986, Month.FEBRUARY, 2));
        //Client client = new Client(2,2,"Laura Mercier","London",69883325,"laura@gmail.com", LocalDate.of(1999, Month.FEBRUARY, 11));
        // Client client = new Client(3, 3, "Giorgio Armani", "Birmingham", 69772255, "armanig@gmail.com", LocalDate.MIN.of(1989, Month.OCTOBER, 10));
        Client client = new Client(5, 4, "Janni Vero", "Liverpool", 67889922, "janniers@gmail.com", LocalDate.of(2000, Month.SEPTEMBER, 7));
        // ClientDaoImpl instanceDao = new ClientDaoImpl();
        boolean expResult = true;
        boolean result = instanceDao.save(client);
        assertEquals("The save test did passed successfully", expResult, result);

    }

    /**
     * Test of update method, of class ClientDaoImpl.
     */
    @Test
    public void testUpdate() {
        try {
            System.out.println("update");
            Client client = new Client(4, 4, "JannyUpdated", "Liverpool", 5555552, "janniers@gmail.com", LocalDate.of(2000, Month.SEPTEMBER, 7));
            boolean expResult = true;
            boolean result = instanceDao.update(client);
            assertEquals(expResult, result);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    /**
     * Test of delete method, of class ClientDaoImpl.
     */
    @Test
    public void testDelete() throws Exception {
        System.out.println("delete");
        Client client = new Client(4, 4, "Janni Versace", "Liverpool", 67889922, "janniers@gmail.com", LocalDate.of(2000, Month.SEPTEMBER, 7));
        boolean expResult = true;
        boolean result = instanceDao.delete(client);
        assertEquals(expResult, result);
    }

    /**
     * Test of findById method, of class ClientDaoImpl.
     */
    @Test
    public void testFindById() throws Exception {
        System.out.println("findById");

        Client expResult = new Client(4, 4, "Janni Versace", "Liverpool", 67889922, "janniers@gmail.com", LocalDate.of(2000, Month.SEPTEMBER, 7));
        Client resultNotExist = instanceDao.findById(100);
        assertNull(resultNotExist);

        Client result = instanceDao.findById(4);
        assertNotEquals(expResult, null);
        assertEquals(expResult, result);
        assertEquals(expResult.getFullname(), result.getFullname());

    }

    /**
     * Test of findByUserId method, of class ClientDaoImpl.
     */
    @Test
    public void testFindByUserId() throws Exception {
        System.out.println("findByUserId");
        Client expResult = new Client(4, 4, "Janni Versace", "Liverpool", 67889922, "janniers@gmail.com", LocalDate.of(2000, Month.SEPTEMBER, 7));
        Client resultNotExist = instanceDao.findByUserId(100);
        assertNull(resultNotExist);

        Client result = instanceDao.findByUserId(4);
        assertNotEquals(expResult, null);
        assertEquals(expResult, result);
        assertEquals(expResult.getFullname(), result.getFullname());

    }

    /**
     * Test of findByFullname method, of class ClientDaoImpl.
     */
    @Test
    public void testFindByFullname() throws Exception {
        System.out.println("findByFullname");
        //String fullName = "";
        boolean expResult = true;
        List< Client> resultNotExist = instanceDao.findByFullname("#Dober#");
        assertTrue(resultNotExist.size() == 0);

        List<Client> result = instanceDao.findByFullname("Janni Versace");
        assertTrue(result.size() > 0);

    }

    /**
     * Test of findAll method, of class ClientDaoImpl.
     */
    @Test
    public void testFindAll() throws Exception {
        System.out.println("findAll");
        int expResult = 4;
        List<Client> result = instanceDao.findAll();
        assertEquals(expResult, result.size());
    }

}
