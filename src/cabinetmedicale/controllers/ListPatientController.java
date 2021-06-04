/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cabinetmedicale.controllers;

import cabinetmedicale.entities.Patient;
import cabinetmedicale.services.PatientService;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ahmed
 */
public class ListPatientController implements Initializable {

    @FXML
    private ImageView add;

    @FXML
    private JFXListView<Patient> list;

    @FXML
    private ImageView back;

    @FXML
    private JFXTextField search;

    void initialize() {
        assert add != null : "fx:id=\"add\" was not injected: check your FXML file 'listPatient.fxml'.";
        assert list != null : "fx:id=\"list\" was not injected: check your FXML file 'listPatient.fxml'.";

    }

    /**
     * Initializes the controller class.
     */
    int userId = LoginController.userId;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        PatientService patientService = new PatientService();

        try {
            list.setItems(patientService.findByCabinetId(userId));
            list.setCellFactory(param -> new ItemCellController());
        } catch (SQLException ex) {
            Logger.getLogger(ListPatientController.class.getName()).log(Level.SEVERE, null, ex);
        }

        search.addEventHandler(KeyEvent.KEY_RELEASED, event -> {
            try {
                list.setItems(patientService.searchByName(userId, search.getText()));
                list.setCellFactory(param -> new ItemCellController());
            } catch (SQLException ex) {
                Logger.getLogger(ListPatientController.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("hi");
        });

        add.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            System.out.println(userId + "liste patient");
            add.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/cabinetmedicale/view/addPatient.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));

            stage.show();

        });

        back.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            System.out.println(userId + "liste patient");
            back.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/cabinetmedicale/view/listRDV.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));

            stage.show();

        });
    }

}
