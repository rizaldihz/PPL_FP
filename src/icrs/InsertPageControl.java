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
public class InsertPageControl {
    
    public static void showInsertForm(){
        InsertForm form = new InsertForm();
        form.setVisible(true);
    }
    
    public static ArrayList<String> getAuthors(){
        ArrayList<String[]> result = new ArrayList<String[]>();
        ArrayList<String> res = new ArrayList<String>();
        result = DBAdapter.getAuthors();
        int length =  result.size();
        for (int i =0;i<length;i++){
            res.add(result.get(i)[1]);
        }
        return res;
    }
    
    public static void main(String args[]) {
//        getAuthors();
    }
    
}