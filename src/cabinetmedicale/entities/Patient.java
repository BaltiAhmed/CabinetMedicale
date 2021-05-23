/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cabinetmedicale.entities;

/**
 *
 * @author Ahmed
 */
public class Patient {
    private int id;
    private int Cid;
    private String fullname;
    private String email;
    private String adresse;
    private String tel;
    private String date;
    

    public Patient() {
    }

    public Patient(int Cid, String fullname, String email, String adresse, String tel, String date) {
        this.Cid = Cid;
        this.fullname = fullname;
        this.email = email;
        this.adresse = adresse;
        this.tel = tel;
        this.date = date;
    }

    public Patient(int id, int Cid, String fullname, String email, String adresse, String tel, String date) {
        this.id = id;
        this.Cid = Cid;
        this.fullname = fullname;
        this.email = email;
        this.adresse = adresse;
        this.tel = tel;
        this.date = date;
    }

    public void setCid(int Cid) {
        this.Cid = Cid;
    }

    public int getCid() {
        return Cid;
    }

    

    public void setId(int id) {
        this.id = id;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getFullname() {
        return fullname;
    }

    public String getEmail() {
        return email;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getTel() {
        return tel;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Patient{" + "id=" + id + ", Cid=" + Cid + ", fullname=" + fullname + ", email=" + email + ", adresse=" + adresse + ", tel=" + tel + ", date=" + date + '}';
    }

    
    
    
    
    
}
