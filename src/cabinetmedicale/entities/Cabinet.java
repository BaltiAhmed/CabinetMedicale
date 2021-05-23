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
public class Cabinet {
    
    private int id;
    private String nom;
    private String specialite;
    private String password;
    private String email;

    public Cabinet() {
    }

    public Cabinet(String nom, String specialite, String password, String email) {
        this.nom = nom;
        this.specialite = specialite;
        this.password = password;
        this.email = email;
    }

    public Cabinet(int id, String nom, String specialite, String password, String email) {
        this.id = id;
        this.nom = nom;
        this.specialite = specialite;
        this.password = password;
        this.email = email;
    }

    

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getSpecialite() {
        return specialite;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Cabinet{" + "id=" + id + ", nom=" + nom + ", specialite=" + specialite + ", password=" + password + ", email=" + email + '}';
    }
    
    

    
    
    
    
}
