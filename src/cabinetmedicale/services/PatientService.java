/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cabinetmedicale.services;

import cabinetmedicale.Iservice.IService;
import cabinetmedicale.entities.Cabinet;
import cabinetmedicale.entities.Patient;
import cabinetmedicale.utils.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Ahmed
 */
public class PatientService implements IService<Patient> {

    private Connection con = DataSource.getInstance().getConnection();
    private Statement ste;

    public PatientService() {

        try {
            ste = con.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void ajouter(Patient t) throws SQLException {
        System.out.println(t);
        String reqins = "INSERT INTO `patient` (`id`,`cid`, `fullname`, `email`, `adresse`, `tel`, `date`) VALUES (NULL,'" + t.getCid() + "', '" + t.getFullname() + "', '" + t.getEmail() + "', '" + t.getAdresse() + "','" + t.getTel() + "','" + t.getDate() + "')";
        ste.executeUpdate(reqins);
    }

    @Override
    public void update(Patient t) throws SQLException {
        System.out.println(t);
        String reqins = "UPDATE `patient` SET `fullname` = '" + t.getFullname() + "', `email` = '" + t.getEmail() + "', `adresse` = '" + t.getAdresse() + "', `tel` = '" + t.getTel() + "', `date` = '" + t.getDate() + "' WHERE `patient`.`id` = '" + t.getId() + "'";
        ste.executeUpdate(reqins);
    }

    @Override
    public void delete(Patient t) throws SQLException {
        System.out.println(t);
        String reqins = "DELETE FROM `patient` WHERE `patient`.`id` = '" + t.getId() + "'";
        ste.executeUpdate(reqins);

        String RDVreqins = "DELETE FROM `rdv` WHERE `rdv`.`idpatient` = '" + t.getId() + "'";
        ste.executeUpdate(RDVreqins);
    }

    @Override
    public Patient findByID(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Patient> readAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    private ObservableList<Patient> patients;

    public ObservableList<Patient> findByCabinetId(int Cid) throws SQLException {

        patients = FXCollections.observableArrayList();
        List<Patient> list = new ArrayList<>();
        String req = "select * from patient where cid='" + Cid + "' ORDER BY id DESC";
        ResultSet rs = ste.executeQuery(req);

        while (rs.next()) {
            int id = rs.getInt("id");
            int cid = rs.getInt("cid");
            String fullname = rs.getString("fullname");
            String email = rs.getString("email");
            String adresse = rs.getString("adresse");
            String tel = rs.getString("adresse");
            String date = rs.getString("date");

            Patient p = new Patient(id, cid, fullname, email, adresse, tel, date);
            patients.addAll(p);

        }
        return patients;
    }

    public ObservableList<Patient> searchByName(int Cid, String name) throws SQLException {

        patients = FXCollections.observableArrayList();
        List<Patient> list = new ArrayList<>();
        String req = "select * from patient where cid='" + Cid + "' AND patient.fullname LIKE  '" + name + "%' ORDER BY id DESC";
        ResultSet rs = ste.executeQuery(req);

        while (rs.next()) {
            int id = rs.getInt("id");
            int cid = rs.getInt("cid");
            String fullname = rs.getString("fullname");
            String email = rs.getString("email");
            String adresse = rs.getString("adresse");
            String tel = rs.getString("adresse");
            String date = rs.getString("date");

            Patient p = new Patient(id, cid, fullname, email, adresse, tel, date);
            patients.addAll(p);

        }
        return patients;
    }

    public List<Patient> find(String Pemail) throws SQLException {
        List<Patient> list = new ArrayList<>();
        String req = "select * from patient where email='" + Pemail + "'";
        ResultSet rs = ste.executeQuery(req);

        while (rs.next()) {

            int id = rs.getInt("id");
            int cid = rs.getInt("cid");
            String fullname = rs.getString("fullname");
            String email = rs.getString("email");
            String adresse = rs.getString("email");
            String tel = rs.getString("adresse");
            String date = rs.getString("date");

            Patient p = new Patient(id, cid, fullname, email, adresse, tel, date);
            list.add(p);

        }
        return list;
    }

}
