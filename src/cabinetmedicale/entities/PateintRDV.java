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
public class PateintRDV {

    private int Cid;
    private String fullname;
    private String email;
    private String adresse;
    private String tel;
    private String date;

    private int id;
    private int cabinetId;
    private int patientId;
    private String dateRDV;
    private String note;

    public PateintRDV(int Cid, String fullname, String email, String adresse, String tel, String date, int id, int cabinetId, int patientId, String dateRDV, String note) {
        this.Cid = Cid;
        this.fullname = fullname;
        this.email = email;
        this.adresse = adresse;
        this.tel = tel;
        this.date = date;
        this.id = id;
        this.cabinetId = cabinetId;
        this.patientId = patientId;
        this.dateRDV = dateRDV;
        this.note = note;
    }

    public int getCid() {
        return Cid;
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

    public int getId() {
        return id;
    }

    public int getCabinetId() {
        return cabinetId;
    }

    public int getPatientId() {
        return patientId;
    }

    public String getDateRDV() {
        return dateRDV;
    }

    public String getNote() {
        return note;
    }

    public void setCid(int Cid) {
        this.Cid = Cid;
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

    public void setId(int id) {
        this.id = id;
    }

    public void setCabinetId(int cabinetId) {
        this.cabinetId = cabinetId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public void setDateRDV(String dateRDV) {
        this.dateRDV = dateRDV;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "PateintRDV{" + "Cid=" + Cid + ", fullname=" + fullname + ", email=" + email + ", adresse=" + adresse + ", tel=" + tel + ", date=" + date + ", id=" + id + ", cabinetId=" + cabinetId + ", patientId=" + patientId + ", dateRDV=" + dateRDV + ", note=" + note + '}';
    }
    
    

}
