
package com.habravanEnterprise.fitnessForLife.db;

import java.sql.Connection;
import java.sql.SQLException;
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
public class SingletonJDBCDataSourceTest {
    
    private  static SingletonJDBCDataSource  instance = null;
    private Connection conn = null;
    
    
    public SingletonJDBCDataSourceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
     @Test
    public void testareaDataSource() {
        
        testGetInstance ();
        testLoadProperties();
        testLoadDriver();
        testOpenConnection();
      //testGetConnection();
    
    
    }

    /**
     * Test of getInstance method, of class SingletonJDBCDataSource.
     */
   
    public void testGetInstance() {
        System.out.println("getInstance");
        instance = SingletonJDBCDataSource.getInstance();
        assertNotNull(instance);
        
        SingletonJDBCDataSource  instance2= SingletonJDBCDataSource.getInstance();
        assertTrue(  " It is the same instance- Singleton" , instance2==instance);
        
    }
    
    
    
    
    /**
     * Test of loadProperties method, of class SingletonJDBCDataSource.
     */
  
    public void testLoadProperties() {
        System.out.println("loadProperties");
        
        
        boolean expResult = true;
        boolean result = instance.loadProperties();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of getConnection method, of class SingletonJDBCDataSource.
     */
    
//  public void testGetConnection()  throws Exception{
//      System.out.println("getConnection");
//      
//     boolean expResult = true;
//     boolean result = instance.openConnection();
//       assertEquals(expResult, result);
//       try{
//           conn= instance.getConnection();
//            assertNotNull("It doesn't have to be null", conn);
//            
//       }catch( Exception e){
//    assertNull (e);
//}
//        
//      }
   
    /**
     * Test of openConnection method, of class SingletonJDBCDataSource.
     */
    
    public void testOpenConnection() {
        System.out.println("openConnection");
        
        boolean expResult = true;
        boolean result = instance.openConnection();
         assertEquals(expResult, result);
        try{
             conn = instance.getConnection();
                assertNotNull( conn );

        }catch( SQLException e) {
            assertNull(e);
            
        
        }
        
    } 

    /**
     * Test of loadDriver method, of class SingletonJDBCDataSource.
     */
   
    public void testLoadDriver() {
        System.out.println("loadDriver");
        boolean expResult = true;
        boolean result = instance.loadDriver();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getConnection method, of class SingletonJDBCDataSource.
     */
   
}
