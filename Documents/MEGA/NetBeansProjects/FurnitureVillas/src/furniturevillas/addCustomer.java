/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package furniturevillas;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author study
 */
public class addCustomer {
    public void addCust(String customer,Integer phone_number, 
            String address,String item,Integer quantity,float cost,String adding_date) {
        java.sql.Connection conn = null;
        System.out.println("Connecting to the Database");
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = java.sql.DriverManager.getConnection(
                    "jdbc:mysql://localhost/furniture?user=root&password=root");
        } catch (Exception e) {
            System.err.println(e);
            System.exit(0);
        }

        try {
            PreparedStatement p;
            java.util.Date date;
            date = new java.util.Date();
            //java.sql.Timestamp timestamp = new java.sql.Timestamp(date.getTime());
            p = conn.prepareStatement("Insert Into person set customer=?,"
                    + "phone_No=? ,address =? ,item =? ,"
                    + "quantities =?,cost=?,dates=?");
            p.setString(1, customer);
            p.setInt(2, phone_number);
            p.setString(3, address);
            p.setString(4, item);
            p.setInt(5, quantity);
            p.setFloat(6, cost);
            p.setString(7,  adding_date);
            p.execute(); //use execute if no results expected back

        } catch (SQLException e) {
            System.err.println("Error " + e.toString());
            return;
        }
    }

    public void showAll() {
        java.sql.Connection conn = null;
        System.out.println("Connecting...");
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = java.sql.DriverManager.getConnection(
                    "jdbc:mysql://localhost/furniture?user=root&password=root");
        } catch (ClassNotFoundException | IllegalAccessException
                | InstantiationException | SQLException e) {
            System.err.println(e);
            System.exit(0);
        }
        System.out.println("Connection established");
        try {
            java.sql.Statement s = conn.createStatement();
            java.sql.ResultSet r = s.executeQuery("SELECT * FROM person");
            while (r.next()) {
                System.out.println(
                        r.getString("customer") + " "
                        + r.getInt("phone_No") + " "
                        + r.getString("address") + " "
                        + r.getString("item") + " "
                        + r.getInt("quantities") + " "
                        + r.getFloat("cost") + " "
                        + r.getString("dates")
                );
            }
            r.close();
            s.close();
            conn.close();
        } catch (SQLException e) {
            System.err.println(e);
            System.exit(0);
        }
    }

    public void deleteRecord(String customer,
            Integer phone_number, String adding_date) {
        java.sql.Connection conn = null;
        System.out.println("Connecting to the Database");
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = java.sql.DriverManager.getConnection(
                    "jdbc:mysql://localhost/furniture?user=root&password=root");
        } catch (Exception e) {
            System.err.println(e);
            System.exit(0);
        }

        try {
            PreparedStatement p;
            p = conn.prepareStatement("Delete from Transfer where customer=? and "
                    + "phone_No=?, dates=?");
            p.setString(1, customer);
            p.setInt(2, phone_number);
            p.setString(3, adding_date);
            p.execute(); //use execute if no results expected back
        } catch (SQLException e) {
            System.err.println("Error " + e.toString());
            return;
        }
    }

    public static void main(String[] args) {
        addCustomer new1 = new addCustomer();
        //new1.addCust();
        //new1.showAll();
        //new1.deleteRecord();
    }

    
}
