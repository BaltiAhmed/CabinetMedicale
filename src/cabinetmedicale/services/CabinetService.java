/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cabinetmedicale.services;

import cabinetmedicale.Iservice.IService;
import cabinetmedicale.utils.DataSource;
import cabinetmedicale.entities.Cabinet;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ahmed
 */
public class CabinetService implements IService<Cabinet> {

    private Connection con = DataSource.getInstance().getConnection();
    private Statement ste;

    public CabinetService() {

        try {
            ste = con.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void ajouter(Cabinet t) throws SQLException {
        System.out.println(t);
        String reqins = "INSERT INTO `cabinet` (`id`, `nom`, `specialite`, `password`, `email`) VALUES (NULL, '" + t.getNom() + "', '" + t.getSpecialite() + "', '" + t.getPassword() + "','" + t.getEmail() + "')";
        ste.executeUpdate(reqins);
    }

    @Override
    public void update(Cabinet t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Cabinet t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Cabinet findByID(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Cabinet> readAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Cabinet> login(String Cemail, String pwd) throws SQLException {
        List<Cabinet> list = new ArrayList<>();
        String req = "select * from cabinet where email='" + Cemail + "' and password='" + pwd + "'";
        ResultSet rs = ste.executeQuery(req);

        while (rs.next()) {

            int id = rs.getInt("id");
            String nom = rs.getString("nom");
            String specialite = rs.getString("specialite");
            String email = rs.getString("email");
            String password = rs.getString("password");

            Cabinet c = new Cabinet(id, nom, specialite, email, password);
            list.add(c);

        }
        return list;
    }

    public List<Cabinet> find(String Cemail) throws SQLException {
        List<Cabinet> list = new ArrayList<>();
        String req = "select * from cabinet where email='" + Cemail + "'";
        ResultSet rs = ste.executeQuery(req);

        while (rs.next()) {

            int id = rs.getInt("id");
            String nom = rs.getString("nom");
            String specialite = rs.getString("specialite");
            String email = rs.getString("email");
            String password = rs.getString("password");

            Cabinet c = new Cabinet(id, nom, specialite, email, password);
            list.add(c);

        }
        return list;
    }

}
