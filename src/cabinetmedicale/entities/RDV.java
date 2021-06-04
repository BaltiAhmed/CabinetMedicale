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
public class RDV {
    private int id;
    private int cabinetId;
    private int patientId;
    private String date;
    private String note;

    public RDV() {
    }

    public RDV(int cabinetId, int patientId, String date, String note) {
        this.cabinetId = cabinetId;
        this.patientId = patientId;
        this.date = date;
        this.note = note;
    }

    public RDV(int id, int cabinetId, int patientId, String date, String note) {
        this.id = id;
        this.cabinetId = cabinetId;
        this.patientId = patientId;
        this.date = date;
        this.note = note;
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

    public void setDate(String date) {
        this.date = date;
    }

    public void setNote(String note) {
        this.note = note;
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

    public String getDate() {
        return date;
    }

    public String getNote() {
        return note;
    }

    @Override
    public String toString() {
        return "RDV{" + "id=" + id + ", cabinetId=" + cabinetId + ", patientId=" + patientId + ", date=" + date + ", note=" + note + '}';
    }
    
    
    
    
}
