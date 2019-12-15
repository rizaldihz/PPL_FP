/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icrs;

/**
 *
 * @author rizaldi
 */
public class Artikel {
    private int id;
    private String judul;
    private String penulis;
    private String tahun;
    private String abstraksi;
    private String referensi;
    private String keyword;
    
    public Artikel(){
        
    }
    public void setArtikel(String judul, String penulis, String tahun, String abstraksi, String referensi, String keyword){
        this.judul = judul;
        this.penulis = penulis;
        this.tahun = tahun;
        this.abstraksi = abstraksi;
        this.keyword = keyword;
        this.referensi = referensi;
    }
    
    public int getID(){
        return this.id;
    }
    
    public String getJudul(){
        return this.judul;
    }
    
    public String getPenulis(){
        return this.penulis;
    }
    
    public String getTahun(){
        return this.tahun;
    }
    
    public String getAbstraksi(){
        return this.abstraksi;
    }
    
    public String getReferensi(){
        return this.referensi;
    }
    
    public String getKeyword(){
        return this.keyword;
    }
}
