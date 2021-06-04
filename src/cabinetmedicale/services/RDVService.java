/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cabinetmedicale.services;

import cabinetmedicale.Iservice.IService;
import cabinetmedicale.entities.Cabinet;
import cabinetmedicale.entities.PateintRDV;
import cabinetmedicale.entities.Patient;
import cabinetmedicale.entities.RDV;
import cabinetmedicale.utils.DataSource;
import com.email.durgesh.Email;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.mail.MessagingException;

/**
 *
 * @author Ahmed
 */
public class RDVService implements IService<RDV> {

    private Connection con = DataSource.getInstance().getConnection();
    private Statement ste;

    public RDVService() {

        try {
            ste = con.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void ajouter(RDV t) throws SQLException {
        System.out.println(t);
        String reqins = "INSERT INTO `rdv` (`id`, `idcabinet`, `idpatient`, `date`, `note`) VALUES (NULL, '" + t.getCabinetId() + "', '" + t.getPatientId() + "', '" + t.getDate() + "', '" + t.getNote() + "');";
        ste.executeUpdate(reqins);
        senEmail(t.getPatientId(), t.getCabinetId(), t.getDate());
    }

    @Override
    public void update(RDV t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(RDV t) throws SQLException {
        System.out.println(t);
        String reqins = "DELETE FROM `rdv` WHERE `rdv`.`id` = '" + t.getId() + "'";
        ste.executeUpdate(reqins);
    }

    @Override
    public RDV findByID(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RDV> readAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    private ObservableList<PateintRDV> rdv;

    public ObservableList<PateintRDV> findByCabinetId(int Cid, String d) throws SQLException {

        rdv = FXCollections.observableArrayList();
        List<PateintRDV> list = new ArrayList<>();
        String req = "SELECT * FROM patient INNER JOIN rdv ON patient.id = rdv.idpatient WHERE patient.cid = '" + Cid + "' AND rdv.date = '"+d+"' ";
        ResultSet rs = ste.executeQuery(req);

        while (rs.next()) {

            int idP = rs.getInt(1);
            int cidP = rs.getInt(2);
            String fullname = rs.getString(3);
            String email = rs.getString(4);
            String adresse = rs.getString(5);
            String tel = rs.getString(6);
            String dateP = rs.getString(7);

            int idRDV = rs.getInt(8);
            int cidRDV = rs.getInt(9);
            int pidRDV = rs.getInt(10);

            String dateRDV = rs.getString(11);
            String note = rs.getString(12);

            PateintRDV PRDV = new PateintRDV(cidP, fullname, email, adresse, tel, dateP, idRDV, cidRDV, pidRDV, note, dateRDV);
            rdv.addAll(PRDV);

        }
        return rdv;
    }

    public void senEmail(int PId, int CId, String RDVDate) throws SQLException {

        String Preq = "select * from patient where id='" + PId + "'";
        ResultSet Prs = ste.executeQuery(Preq);
        Patient p = null;
        while (Prs.next()) {
            int id = Prs.getInt("id");
            int cid = Prs.getInt("cid");
            String fullname = Prs.getString("fullname");
            String Pemail = Prs.getString("email");
            String adresse = Prs.getString("adresse");
            String tel = Prs.getString("adresse");
            String date = Prs.getString("date");

            p = new Patient(id, cid, fullname, Pemail, adresse, tel, date);
        }
        String Creq = "select * from cabinet where id='" + CId + "'";
        ResultSet Crs = ste.executeQuery(Creq);
        Cabinet c = null;
        while (Crs.next()) {
            int Cid = Crs.getInt("id");
            String nom = Crs.getString("nom");
            String specialite = Crs.getString("specialite");
            String Cemail = Crs.getString("email");
            String password = Crs.getString("password");

            c = new Cabinet(Cid, nom, specialite, password, Cemail);
        }

        Email email = new Email("darragino1@gmail.com", "tarajidawla1919");

        try {
            email.setFrom("ahmedbalti06@gmail.com", "");
            email.setSubject("RDV");
            email.setContent("<div><h2>Bonjour '" + p.getFullname() + "'</h2><br><h3>Votre RDV chez Dr '" + c.getNom() + "' sera le '" + RDVDate + "'</h3></div>", "text/html");
            email.addRecipient(p.getEmail());
            email.send();
        } catch (MessagingException ex) {
            Logger.getLogger(RDVService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(RDVService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
