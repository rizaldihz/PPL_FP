/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icrs;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author rizaldi
 */
public class InsertPageControl {
    
    public static void showInsertForm(){
        InsertForm form = new InsertForm();
        form.setVisible(true);
    }
    
    public static void showInsertPage(){
        InsertPage page = new InsertPage();
        page.setVisible(true);
    }
    
    public static void loadTable(InsertPage page, String col, String condition, int no)
    {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("No");
        model.addColumn("Judul");
        model.addColumn("Tahun");
        model.addColumn("Penulis");
        model.addColumn("Keyword");
        model.addColumn("Opsi");
        model.addColumn("");
        
        int i=1;
        ResultSet res = DBAdapter.getMetadata(col, condition, no);
        try{
            page.showProgress(true);
            while(res.next()){
                Artikel ini = new Artikel();
                ini.setArtikel(res.getString("judul"), res.getString("penulis"), res.getString("tahun"), res.getString("abstraksi"), res.getString("referensi"), res.getString("keyword"));
                model.addRow(new Object[]{i++,res.getString("judul"),res.getString("tahun"),res.getString("penulis"),res.getString("keyword"),"Detail",ini});
                page.setProgress(i*10);
            }
            page.setTableData(model);
            page.setProgress(100);
            page.showProgress(false);
        }catch(SQLException e){
            page.setTableData(model);
            System.err.println(e);
        }
    }
    
    public static void showMessageSuccess()
    {
        SuccessMessage message = new SuccessMessage();
        message.setVisible(true);
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
    
    public static int insertArtikel(String judul, String tahun, String keyword, String penulis, String abstrak, String referensi, InsertForm form)
    {
        Artikel artikel = new Artikel();
        artikel.setArtikel(judul, penulis, tahun, abstrak, referensi, keyword);
        int insert;
        
        form.showProgress(true);
        form.setProgress(1);
        insert = DBAdapter.insertArtikel(artikel);
        form.setProgress(100);
        showMessageSuccess();
        form.setVisible(false);
        return insert;
    }
    
    public static void showDetail(Artikel artikel)
    {
        DetailArtikel detail = new DetailArtikel();
        detail.setContent(artikel);
        detail.setVisible(true);
    }
    
    public static void main(String args[]) {
//        getAuthors();
        showInsertPage();
    }
    
}
