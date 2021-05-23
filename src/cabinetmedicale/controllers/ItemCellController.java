/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cabinetmedicale.controllers;

import cabinetmedicale.entities.Patient;
import cabinetmedicale.services.PatientService;
import com.jfoenix.controls.JFXListCell;
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
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ahmed
 */
public class ItemCellController extends JFXListCell<Patient> {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane rootAnchorPane;

    @FXML
    private ImageView iconImageView;

    @FXML
    private Label fullname;

    @FXML
    private Label dateLabel;

    @FXML
    private ImageView delete;

    @FXML
    private ImageView update;

    @FXML
    private Label email;

    @FXML
    private Label tel;

    @FXML
    private Label adresse;

    private FXMLLoader fxmlLoader;

    @FXML
    void initialize() {

    }

    @Override
    public void updateItem(Patient patient, boolean empty) {

        super.updateItem(patient, empty);

        if (empty || patient == null) {
            setText(null);
            setGraphic(null);
        } else {

            if (fxmlLoader == null) {
                fxmlLoader = new FXMLLoader(getClass()
                        .getResource("/cabinetmedicale/view/itemCell.fxml"));
                fxmlLoader.setController(this);

                try {
                    fxmlLoader.load();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            fullname.setText(patient.getFullname());
            dateLabel.setText(patient.getDate().toString());
            adresse.setText(patient.getAdresse());
            tel.setText(patient.getTel());
            email.setText(patient.getEmail());

            setText(null);
            setGraphic(rootAnchorPane);

            int pid = patient.getId();

            update.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                update.getScene().getWindow().hide();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/cabinetmedicale/view/updatePatient.fxml"));

                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));

                UpdatePatientController UPC = loader.getController();

                UPC.setUserId(pid);

                System.out.println(pid);

                stage.show();

            });

            delete.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                PatientService patientService = new PatientService();
                Patient p = new Patient(patient.getId(), patient.getCid(), patient.getFullname(), patient.getEmail(), patient.getAdresse(), patient.getTel(), patient.getDate());
                try {
                    if (patientService.find(patient.getEmail()).size() != 0) {
                        try {
                            patientService.delete(p);
                            getListView().getItems().remove(getListView().getItems());
                            System.out.println( getListView().getItems().toString());
                            modal("Patient suprimé");
                            
                        } catch (SQLException ex) {
                            Logger.getLogger(SignupController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        modal("Patient n'existe plus");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ItemCellController.class.getName()).log(Level.SEVERE, null, ex);
                }

            });

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
