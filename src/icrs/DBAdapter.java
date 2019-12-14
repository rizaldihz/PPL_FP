/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icrs;


import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author rizaldi
 */
public class DBAdapter {
    
    private static final String USERNAME="root";
    private static final String PASSWORD="";
    private static final String CONN_STRING="jdbc:mysql://localhost:3306/irci"; 
    
    public static Connection getConnection(){
        Connection conn = null;
        Statement stmt;
        
        try {
            conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
        } catch(SQLException e){
            System.err.println(e);
        }            
        return conn;
    }
    
    public static ArrayList<String[]> getAuthors(){
        ArrayList<String[]> result = new ArrayList<String[]>();
        Connection conn;
        Statement stmt;
        try{
            conn = DBAdapter.getConnection();
            stmt = conn.createStatement();
            ResultSet query = stmt.executeQuery("SELECT * from authors");
            while(query.next()){
                String[] data = {query.getString("id"),query.getString("nama"),query.getString("alamat"),query.getString("jk")};
                System.err.println(data[1]);
                result.add(data);
            }
        }catch(SQLException e){
            System.err.println(e);
        }
        return result;
    }

    public static void main(String args[]) {
//        getAuthors();
    }
    
}
