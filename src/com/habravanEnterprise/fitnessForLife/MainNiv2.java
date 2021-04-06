package com.habravanEnterprise.fitnessForLife;

import com.habravanEnterprise.fitnessForLife.db.SingletonJDBCDataSource;
import com.mysql.jdbc.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dorina
 */
// Clasa am facut-o dupa testarea Singleton pentru ca nu reuseam sa vad datele din baza de date.
public class MainNiv2 {

    public static void main(String[] args) {

        try {
            SingletonJDBCDataSource dataSource = SingletonJDBCDataSource.getInstance();
            // Tabela din care vrem sa selectam datele.
            String sql = "SELECT * FROM user";

            Connection conn = (Connection) dataSource.getConnection();
            Statement stat = conn.createStatement();

            ResultSet rs = stat.executeQuery(sql);

            while (rs.next()) {
//
//                int customerid = rs.getInt(1);
//                int userid = rs.getInt(2);
//                String fullname = rs.getString(3);
//                String location = rs.getString(4);
//                int phone_number = rs.getInt(5);
//                String email_address = rs.getString(6);      
//                LocalDate date_of_birth = rs.getDate(7).toLocalDate();
                

                                                

            int id =rs.getInt(1);
            String username = rs.getString(2);
            String password = rs.getString(3);
            LocalDate date  = rs.getDate(4).toLocalDate();
            
                //printRecord(customerid, userid, fullname, location, phone_number, email_address, date_of_birth );
                
                printRecord(id, username,password,date);

            }

        } catch (SQLException ex) {
            Logger.getLogger(MainNiv2.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private static void printRecord (int id, String username, String password ,LocalDate date) {
        System.out.println("id = "+id+", username = "+username+" , password = "+password+", date = "+date);

    }
    
    
//    private static void printRecord(int customerid, int userid, String fullname, String location, int phone_number, String email_address, LocalDate date_of_birth) {
//
//        System.out.println("customerid = " + customerid + " , userid = " + userid + " , fullname = " + fullname + " , location = " + location + " , phone_number=" + phone_number + " ,"
//                + "email_address=" + email_address + " , date_of_birth= " + date_of_birth);
//    }

}
