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
    
    public static int insertArtikel(Artikel artikel)
    {
        Connection conn;
        Statement stmt;
        boolean usernameExists = false;        
        int result = 0;
        try {
            conn = getConnection();
            stmt = conn.createStatement();
//            ResultSet query = stmt.executeQuery("SELECT * FROM book");
//            while(query.next()){
//                int idmember = Integer.parseInt(query.getString("idbook"));
//                if(idmember==temp){
//                    System.out.println("Sama");
//                    usernameExists = true;
//                    break;
//                }
//            }

            String insert = "INSERT INTO metadata(judul,penulis,tahun,abstraksi,referensi,keyword) VALUES('"+artikel.getJudul()+"','"+artikel.getPenulis()+"', '"+artikel.getTahun()+"', '"+artikel.getAbstraksi()+"', '"+artikel.getReferensi()+"', '"+artikel.getKeyword()+"');";
            stmt.executeUpdate(insert);
//            MainControl.openDialogueBox("Data buku berhasil ditambahkan", 10, dataBuku.getIdBook(), "buku");
            result = 1;

        } catch(SQLException e){
            System.err.println(e);
        }    
        return result;
    }
    
    public static ResultSet getMetadata(String col,String condition,int page)
    {
        Connection conn;
        Statement stmt;
        ResultSet query = null;
        try{
            conn = DBAdapter.getConnection();
            stmt = conn.createStatement();
            String where = "";
            if(condition!=null && col!=null){
                where = where + " where "+col+" like '%"+condition+"%'";
            }
            page = page*10;
            query = stmt.executeQuery("SELECT * from metadata"+where+" limit "+page+",10;");
        }catch(SQLException e){
            System.err.println(e);
        }
        return query;
    }

    public static void main(String args[]) {
//        getAuthors();
        getMetadata(null,null,0);
    }
    
}
