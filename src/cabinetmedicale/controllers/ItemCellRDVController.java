/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cabinetmedicale.controllers;

import cabinetmedicale.entities.PateintRDV;
import cabinetmedicale.entities.Patient;
import cabinetmedicale.entities.RDV;
import cabinetmedicale.services.PatientService;
import cabinetmedicale.services.RDVService;
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
public class ItemCellRDVController extends JFXListCell<PateintRDV> {

    @FXML
    private AnchorPane rootAnchorPane;
    @FXML
    private ImageView iconImageView;
    @FXML
    private Label date;
    @FXML
    private ImageView delete;
    @FXML
    private ImageView update;
    @FXML
    private Label note;
    
      @FXML
    private Label name;

    @FXML
    private Label tel;

    /**
     * Initializes the controller class.
     */
    private FXMLLoader fxmlLoader;

    @Override
    public void updateItem(PateintRDV rdv, boolean empty) {

        super.updateItem(rdv, empty);

        if (empty || rdv == null) {
            setText(null);
            setGraphic(null);
        } else {

            if (fxmlLoader == null) {
                fxmlLoader = new FXMLLoader(getClass()
                        .getResource("/cabinetmedicale/view/itemCellRDV.fxml"));
                fxmlLoader.setController(this);

                try {
                    fxmlLoader.load();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            date.setText(rdv.getDateRDV());
            note.setText(rdv.getNote());
            name.setText(rdv.getFullname());
            tel.setText(rdv.getTel());

            setText(null);
            setGraphic(rootAnchorPane);

            int rid = rdv.getId();

            delete.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                RDVService rdvService = new RDVService();
                RDV r = new RDV(rdv.getId(), rdv.getCabinetId(), rdv.getPatientId(), rdv.getDate(), rdv.getNote());

                try {
                    rdvService.delete(r);
                    showListRDVScreen();
                    modal("RDV annulé");
                } catch (SQLException ex) {
                    Logger.getLogger(ItemCellRDVController.class.getName()).log(Level.SEVERE, null, ex);
                }

            });

        }

    }

    void showListRDVScreen() {
        delete.getScene().getWindow().hide();
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
