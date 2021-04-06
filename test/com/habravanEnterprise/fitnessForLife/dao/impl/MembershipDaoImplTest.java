package com.habravanEnterprise.fitnessForLife.dao.impl;

import com.habravanEnterprise.fitnessForLife.dao.MembershipDaoIntf;
import com.habravanEnterprise.fitnessForLife.db.SingletonJDBCDataSource;
import com.habravanEnterprise.fitnessForLife.db.StaticPropertyClass;
import com.habravanEnterprise.fitnessForLife.models.Membership;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
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
public class MembershipDaoImplTest {

    private static final Logger LOG = Logger.getLogger(MembershipDaoImplTest.class.getName());

    private static SingletonJDBCDataSource instance = null;
    private Connection conn = null;
    private MembershipDaoIntf instanceDao = null;

    private static boolean DEBUG_temp = true;

    public MembershipDaoImplTest() {
        System.out.println("MembershipDaoImplTest");
        instance = SingletonJDBCDataSource.getInstance();
        instanceDao = new MembershipDaoImpl();

    }

    @BeforeClass
    public static void setUpClass() {
        System.out.println("SetUpClass");
        DEBUG_temp = StaticPropertyClass.DEBUG;

        StaticPropertyClass.DEBUG = true;

    }

    @AfterClass
    public static void tearDownClass() {

        StaticPropertyClass.DEBUG = DEBUG_temp;

    }

    @Before
    public void setUp() throws SQLException {

        try {
           
            conn = instance.getConnection();

            System.out.println("Connection established.....");
             Statement st = conn.createStatement();

            String sqlCreate = "CREATE TABLE membership (\n"
                    + "  `ID_Membership` int(11) NOT NULL AUTO_INCREMENT,\n"
                    + "  `Membership_Type` varchar(155) DEFAULT NULL,\n"
                    + "  `Membership_Period` varchar(155) DEFAULT NULL,\n"
                    + "  `Instructor_Name` varchar(155) DEFAULT NULL,\n"
                    + "  PRIMARY KEY (`ID_Membership`)\n"
                    + ") ENGINE=InnoDB DEFAULT CHARSET=utf8;";

            st.executeUpdate(sqlCreate);
            st.close();

            st = conn.createStatement();
            System.out.println("Created table");

            String sqlInsert = "INSERT INTO membership "
                    + " VALUES (1,'OFF-PEAK','Monthly','Johnatan Gregor'),(2,'PLUS','Monthly','George Jameson');";

            st.executeUpdate(sqlInsert);
            st.close();

            System.out.println("The data inserted in the table");

        } catch (SQLException ex) {
            LOG.severe(ex.toString());
            throw ex;
        }

    }

    @After
    public void tearDown() {

        try {
            conn = instance.getConnection();
            System.out.println("tearDown() ");

            Statement st = conn.createStatement();

            String sqlDrop = "DROP TABLE IF EXISTS membership ;";
            st.executeUpdate(sqlDrop);
            st.close();

            System.out.println("The table membership has been deleted");
        } catch (SQLException ex) {
            Logger.getLogger(MembershipDaoImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Test of save method, of class MembershipDaoImpl.
     */
    @Test
    public void testSave() throws Exception {
        System.out.println("save");
        Membership membership = new Membership(3, "OFF-PEAK3", "Monthly3", "Johnatan Gregor3");
        boolean expResult = true;
        boolean result = instanceDao.save(membership);
        assertEquals(expResult, result);

    }

    /**
     * Test of update method, of class MembershipDaoImpl.
     */
    @Test
    public void testUpdate() throws Exception {
        System.out.println("update");
        Membership membership = new Membership(1, "OFF-PEAK-Updated", "Monthly-Updated", "Johnatan Gregor-Updated");
        boolean expResult = true;
        boolean result = instanceDao.update(membership);
        assertEquals(expResult, result);
    }

    /**
     * Test of delete method, of class MembershipDaoImpl.
     */
    @Test
    public void testDelete() throws Exception {
        System.out.println("delete");
        Membership membership = new Membership(1, "OFF-PEAK", "Monthly", "Johnatan Gregor");
        boolean expResult = true;
        boolean result = instanceDao.delete(membership);
        assertEquals(expResult, result);

    }

    /**
     * Test of findByMembershipID method, of class MembershipDaoImpl.
     */
   @Test
    public void testFindByMembershipID() throws Exception {
        System.out.println("findByMembershipID");

        Membership expResult = new Membership(1, "OFF-PEAK", "Monthly", "Johnatan Gregor");

        Membership resultNotExist = instanceDao.findByMembershipID(100);
        assertNull(resultNotExist);

        Membership result = instanceDao.findByMembershipID(1);
        assertEquals(expResult,result );

    }

    /**
     * Test of findByMembershipType method, of class MembershipDaoImpl.
     */
    @Test
    public void testFindByMembershipType() throws Exception {
        System.out.println("findByMembershipType");
        int expResult = 1;

        List<Membership> resultNotExist = instanceDao.findByMembershipType("#Pltd");
        assertTrue(resultNotExist.size() == 0);

        List<Membership> result = instanceDao.findByMembershipType("OFF-PEAK");
        assertEquals(expResult,result.size());
        
    }

    /**
     * Test of findAll method, of class MembershipDaoImpl.
     */
    @Test
    public void testFindAll() throws Exception {
        System.out.println("findAll");
        int expResult = 2;
        List<Membership> result = instanceDao.findAll();
         assertEquals(expResult,result.size());
         
         String expType= "PLUS";
         assertEquals(expType,result.get(1).getMembershipType());
         
         
    }

}
