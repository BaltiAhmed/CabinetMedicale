/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cabinetmedicale.controllers;

import cabinetmedicale.entities.Cabinet;
import cabinetmedicale.entities.Patient;
import cabinetmedicale.services.CabinetService;
import cabinetmedicale.services.PatientService;
import com.jfoenix.controls.JFXButton;
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
import javafx.scene.control.DatePicker;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ahmed
 */
public class AddPatientController implements Initializable {

    @FXML
    private JFXTextField name;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXTextField adresse;
    @FXML
    private DatePicker date;
    @FXML
    private JFXButton submit;
    @FXML
    private JFXTextField tel;

    @FXML
    private ImageView back;

    /**
     * Initializes the controller class.
     */
    int userId = LoginController.userId;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        System.out.println(userId + "add patient");
        back.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            back.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/cabinetmedicale/view/listPatient.fxml"));

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

        submit.setOnAction(event -> {

            if (!name.getText().equals("") || !email.getText().equals("") || !adresse.getText().equals("") || !tel.getText().equals("") || !date.getValue().toString().equals("")) {

                try {
                    System.out.println(userId + "add ");
                    addPatient(name.getText(), email.getText(), adresse.getText(), tel.getText(), date.getValue().toString());
                } catch (SQLException ex) {
                    Logger.getLogger(SignupController.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                modal("Vérifier vos inputs");
            }

        });

    }

    void addPatient(String fullname, String email, String adresse, String tel, String date) throws SQLException {
        System.out.println(userId);
        PatientService patientService = new PatientService();
        Patient patient = new Patient(userId, fullname, email, adresse, tel, date);
        if (patientService.find(email).size() == 0) {
            try {
                patientService.ajouter(patient);
                modal("Patient ajoutée");
            } catch (SQLException ex) {
                Logger.getLogger(SignupController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            modal("Patient exist!!");
        }

    }

    void modal(String text) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/cabinetmedicale/view/message.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));

        MessageController message = loader.getController();

        message.setText(text);

        stage.showAndWait();
    }

}
