/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cabinetmedicale.controllers;

import cabinetmedicale.entities.Patient;
import cabinetmedicale.entities.RDV;
import cabinetmedicale.services.PatientService;
import cabinetmedicale.services.RDVService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
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
public class AddRDVController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView back;

    @FXML
    private DatePicker date;

    @FXML
    private JFXButton submit;

    @FXML
    private JFXTextArea note;

    @FXML
    void initialize() {

    }

    private int patientId;

    void setPatientId(int id) {
        patientId = id;
    }

    int userId = LoginController.userId;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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

            if (!note.getText().equals("") || !date.getValue().toString().equals("")) {

                System.out.println(userId + "add ");
                try {
                    addRDV(date.getValue().toString(),note.getText());
                } catch (SQLException ex) {
                    Logger.getLogger(AddRDVController.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                modal("Vérifier vos inputs");
            }

        });

    }

    void addRDV(String date, String note) throws SQLException {
        System.out.println(userId);
        RDVService rdvService = new RDVService();
        RDV rdv = new RDV(userId, patientId, date, note);

        try {
            rdvService.ajouter(rdv);
            modal("RDV ajoutée");
        } catch (SQLException ex) {
            Logger.getLogger(SignupController.class.getName()).log(Level.SEVERE, null, ex);
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

        stage.show();
    }

}
